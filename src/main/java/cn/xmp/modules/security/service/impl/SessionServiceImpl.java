package cn.xmp.modules.security.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.xmp.modules.security.entity.ActiveUser;
import cn.xmp.modules.system.entity.SysUser;
import cn.xmp.modules.security.service.ISessionService;
import cn.xmp.modules.common.utils.AddressUtil;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cn.xmp.modules.security.entity.ActiveUser.OFFLINE;
import static cn.xmp.modules.security.entity.ActiveUser.ONLINE;


/**
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {

    private final SessionDAO sessionDAO;

    @Override
    public List<ActiveUser> list(String username) {
        String currentSessionId = (String) SecurityUtils.getSubject().getSession().getId();

        List<ActiveUser> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            ActiveUser activeUser = new ActiveUser();
            SysUser user;
            SimplePrincipalCollection principalCollection;
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                user = (SysUser) principalCollection.getPrimaryPrincipal();
                activeUser.setUsername(user.getUsername());
                activeUser.setUserId(user.getUserId().toString());
            }
            activeUser.setId((String) session.getId());
            activeUser.setHost(session.getHost());
            activeUser.setStartTimestamp(DateUtil.format(session.getStartTimestamp(), DatePattern.NORM_DATETIME_PATTERN));
            activeUser.setLastAccessTime(DateUtil.format(session.getLastAccessTime(), DatePattern.NORM_DATETIME_PATTERN));
            long timeout = session.getTimeout();
            activeUser.setStatus(timeout == 0L ? OFFLINE : ONLINE);
            String address = AddressUtil.getCityInfo(activeUser.getHost());
            activeUser.setLocation(address);
            activeUser.setTimeout(timeout);
            if (StringUtils.equals(currentSessionId, activeUser.getId())) {
                activeUser.setCurrent(true);
            }
            if (StringUtils.isBlank(username)
                    || StringUtils.equalsIgnoreCase(activeUser.getUsername(), username)) {
                list.add(activeUser);
            }
        }
        return list;
    }

    @Override
    public void forceLogout(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        session.stop();
        sessionDAO.delete(session);
    }

    @Override
    public List<SimplePrincipalCollection> getPrincipals(@NonNull Long userId) {
        List<SimplePrincipalCollection> collections = Lists.newArrayList();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
                SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (simplePrincipalCollection != null) {
                    SysUser user = (SysUser) simplePrincipalCollection.getPrimaryPrincipal();
                    if (userId.equals(user.getUserId())) {
                        collections.add(simplePrincipalCollection);
                    }
                }
            }
        }
        return collections;
    }
}

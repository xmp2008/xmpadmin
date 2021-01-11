package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.Dept;
import cn.xmp.modules.system.entity.dept.DeptTree;
import cn.xmp.modules.system.model.request.DeptPageParam;
import cn.xmp.modules.system.request.DeptRequest;
import cn.xmp.modules.system.service.IDeptService;
import cn.xmp.modules.system.utils.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/dept")
public class DeptAdmin {


    private IDeptService deptService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody DeptRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add Dept: {}", request);
        try {
            Dept bean = new Dept();
            BeanUtils.copyProperties(request, bean);
            response = deptService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody DeptRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update Dept: {}", request);
        try {
            Dept bean = new Dept();
            BeanUtils.copyProperties(request, bean);
            response = deptService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody DeptRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query Dept : {}", request);
        try {
            Dept bean = new Dept();
            BeanUtils.copyProperties(request, bean);
            response = deptService.query(bean);
            log.info("query Dept back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody DeptRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete Dept : {}", request);
        try {
            Dept bean = new Dept();
            BeanUtils.copyProperties(request, bean);
            response = deptService.delete(bean);
            log.info("delete Dept back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody DeptPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("Dept page method request : {}", request);
            response = deptService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse list(@RequestBody DeptRequest request) {
        BaseResponse response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        //业务操作
        log.info("list Dept : {}", request);
        try {
            Dept bean = new Dept();
            BeanUtils.copyProperties(request, bean);
            List<Dept> list = deptService.list(new QueryWrapper<>());
            List<DeptTree<Dept>> trees = this.convertDept(list);
            List<DeptTree<Dept>> deptTrees = TreeUtil.buildDeptTree(trees);
            if (CollectionUtils.isEmpty(deptTrees)) {
                response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            } else {
                response.setDataInfo(deptTrees);
            }
            log.info("list Dept back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    private List<DeptTree<Dept>> convertDept(List<Dept> Dept) {
        List<DeptTree<Dept>> trees = new ArrayList<>();
        Dept.forEach(dept -> {
            DeptTree<Dept> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });
        return trees;
    }
}

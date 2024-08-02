package org.jeecg.modules.bs_test.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.bs_test.entity.BsTest_01;
import org.jeecg.modules.bs_test.service.IBsTest_01Service;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: bs_test_01
 * @Author: jeecg-boot
 * @Date:   2024-08-03
 * @Version: V1.0
 */
@Api(tags="bs_test_01")
@RestController
@RequestMapping("/a/bsTest_01")
@Slf4j
public class BsTest_01Controller extends JeecgController<BsTest_01, IBsTest_01Service> {
	@Autowired
	private IBsTest_01Service bsTest_01Service;

	/**
	 * 分页列表查询
	 *
	 * @param bsTest_01
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bs_test_01-分页列表查询")
	@ApiOperation(value="bs_test_01-分页列表查询", notes="bs_test_01-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent = "modules/bs_test_01/BsTest_01List")
	public Result<IPage<BsTest_01>> queryPageList(BsTest_01 bsTest_01,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BsTest_01> queryWrapper = QueryGenerator.initQueryWrapper(bsTest_01, req.getParameterMap());
		Page<BsTest_01> page = new Page<BsTest_01>(pageNo, pageSize);
		IPage<BsTest_01> pageList = bsTest_01Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param bsTest_01
	 * @return
	 */
	@AutoLog(value = "bs_test_01-添加")
	@ApiOperation(value="bs_test_01-添加", notes="bs_test_01-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:bs_test_01:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BsTest_01 bsTest_01) {
		bsTest_01Service.save(bsTest_01);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param bsTest_01
	 * @return
	 */
	@AutoLog(value = "bs_test_01-编辑")
	@ApiOperation(value="bs_test_01-编辑", notes="bs_test_01-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:bs_test_01:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BsTest_01 bsTest_01) {
		bsTest_01Service.updateById(bsTest_01);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bs_test_01-通过id删除")
	@ApiOperation(value="bs_test_01-通过id删除", notes="bs_test_01-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:bs_test_01:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bsTest_01Service.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bs_test_01-批量删除")
	@ApiOperation(value="bs_test_01-批量删除", notes="bs_test_01-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:bs_test_01:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bsTest_01Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bs_test_01-通过id查询")
	@ApiOperation(value="bs_test_01-通过id查询", notes="bs_test_01-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BsTest_01> queryById(@RequestParam(name="id",required=true) String id) {
		BsTest_01 bsTest_01 = bsTest_01Service.getById(id);
		if(bsTest_01==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bsTest_01);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bsTest_01
    */
    //@RequiresPermissions("org.jeecg.modules.demo:bs_test_01:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BsTest_01 bsTest_01) {
        return super.exportXls(request, bsTest_01, BsTest_01.class, "bs_test_01");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("bs_test_01:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BsTest_01.class);
    }

}

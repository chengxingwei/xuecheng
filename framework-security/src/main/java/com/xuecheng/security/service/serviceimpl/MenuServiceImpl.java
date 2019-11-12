package com.xuecheng.security.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.entities.system.Menu;
import com.xuecheng.entities.system.Role;
import com.xuecheng.enums.ResultTypeEnum;
import com.xuecheng.security.dto.MenuDTO;
import com.xuecheng.security.mapper.MenuMapper;
import com.xuecheng.security.mapper.RoleMapper;
import com.xuecheng.security.service.MenuService;
import com.xuecheng.security.service.RoleService;
import com.xuecheng.utils.Pagination;
import com.xuecheng.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Result list(MenuDTO menuDTO) {
        Result result = new Result();
        result.setCode(0);
        if (Objects.equals(menuDTO.getPageIndex(),0)){//不分页
            List<Menu> menus = menuMapper.list(menuDTO);
            result.setData(menus);
        }else{//分页
            Page page = PageHelper.startPage(menuDTO.getPageIndex(),menuDTO.getPageSize());
            List<Menu> menus = menuMapper.list(menuDTO);
            result.setData(menus);
            Pagination pagination = new Pagination();
            pagination.setTotal(page.getTotal()).setCurrent(menuDTO.getPageIndex()).setPageSize(menuDTO.getPageSize());
            result.setPagination(pagination);
        }
        return result;
    }

    @Override
    public Result save(MenuDTO menuDTO) {
        if (menuDTO.getId() == null){//添加
            Menu menu = new Menu();
            menu.setBtnName(menuDTO.getBtnName());
            menu.setMenuName(menuDTO.getMenuName());
            menu.setMenuPath(menuDTO.getMenuPath());
            menu.setMenuType(menuDTO.getMenuType());
            menu.setSort(menuDTO.getSort());
            menu.setParentId(menuDTO.getParentId());
            menuMapper.insert(menu);
        }else{//修改
            Menu menu = menuMapper.selectById(menuDTO.getId());
            if (menu != null){
                menu.setBtnName(menuDTO.getBtnName());
                menu.setMenuName(menuDTO.getMenuName());
                menu.setMenuPath(menuDTO.getMenuPath());
                menu.setMenuType(menuDTO.getMenuType());
                menu.setSort(menuDTO.getSort());
                menu.setParentId(menuDTO.getParentId());
                menuMapper.update(menu,new QueryWrapper<Menu>().eq("id",menuDTO.getId()));
            }else{
                return new Result(ResultTypeEnum.SUCCESS.toValue(),"菜单不存在",null);
            }
        }
        return new Result(null);
    }

    @Override
    public Result delete(MenuDTO menuDTO) {
        if (menuDTO.getIds() != null && menuDTO.getIds().length > 0){
            for (Long id : menuDTO.getIds()) {
                menuMapper.deleteById(id);
            }
        }else{
            return new Result().setCode(ResultTypeEnum.FAIL.toValue()).setMsg("参数错误");
        }
        return new Result().setMsg("操作成功");
    }
}

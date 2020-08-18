# 一行代码实现RecyclerView
## 简单使用
* 初始化
```
rv_view.buildVerticalLayout<ReAdapter>()
```
* 设置数据
```
  adapter需要实现RecyclerData接口
  rv_view.getBuilder().setData(list)
```
* 空布局
```
启用默认的全局空布局
EmptyView.emptyViewShow=true

设置默认的全局空布局样式
EmptyView.emptyLayout = R.layout.empty_layout

单独为某个rv设置空布局
rv_view.getBuilder().setEmptyView(EmptyView(R.layout.el,this))

```
* 监听

建议配合 https://github.com/zihuan1/view-recycler-adapter

一行代码实现松实现各种监听，单条Item上的多个子view监听，长按等，
并且包含普通适配器，多布局适配器，二级列表适配器，RecyclerBindAdapter等

* 刷新

融合了刷新加载等全功能版本recyclerview
https://github.com/zihuan1/view-complete-recyclerview

* 依赖地址

implementation 'com.github.zihuan1:view-grace-recyclerview:0.5.2'

package com.project.whj.clearntemplate.presentation.ui.constact.base;


/**
 * 主要是将View层和Presener进行联系
 * @param <T>
 */
public interface BaseView<T> {
    void setPresenter(T t);
}

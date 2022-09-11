package com.classic.base_library.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zcq on 17/6/5.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {

}

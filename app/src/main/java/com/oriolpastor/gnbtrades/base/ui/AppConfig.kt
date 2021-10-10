package com.oriolpastor.gnbtrades.base.ui

import com.oriolpastor.gnbtrades.common.remote.ConfigurationProvider

class AppConfig: ConfigurationProvider {
    override val baseUrl: String = "https://quiet-stone-2094.herokuapp.com/"
}
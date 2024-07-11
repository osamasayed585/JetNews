package com.droidos.home.data.model.response

import com.droidos.common.base.BaseResponse
import com.droidos.home.data.model.beans.Article

class ArticlesResponse(val articles: List<Article>) : BaseResponse()

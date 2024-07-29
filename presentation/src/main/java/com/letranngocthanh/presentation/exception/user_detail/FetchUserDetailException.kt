package com.letranngocthanh.presentation.exception.user_detail

import com.letranngocthanh.presentation.exception.BaseException

class FetchUserDetailException(cause: Throwable? = null) :
    BaseException("Unknown Error - Cannot fetch user detail", cause)
package com.letranngocthanh.presentation.exception.users

import com.letranngocthanh.presentation.exception.BaseException

class FetchUsersException(cause: Throwable? = null) : BaseException("Unknown Error - Cannot fetchUsers", cause)
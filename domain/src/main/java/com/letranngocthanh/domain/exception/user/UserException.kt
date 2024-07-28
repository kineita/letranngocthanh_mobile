package com.letranngocthanh.domain.exception.user

class InvalidUserIdException : Exception("User ID cannot be null or empty")

class CannotGetLocalUserDataSource : Exception("Something wrong in local data source")
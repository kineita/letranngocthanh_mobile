package com.letranngocthanh.domain.exception.pagination

class InvalidPageNumberException : Exception("Page number must be greater than zero")
class InvalidPageSizeException : Exception("Page size must be greater than zero")
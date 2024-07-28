package com.letranngocthanh.data.source.user.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.letranngocthanh.data.model.UserDetailEntity
import com.letranngocthanh.data.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY orderIndex LIMIT :limit OFFSET :offset")
    suspend fun getUsersPaginated(limit: Int, offset: Int): List<UserEntity>

    @Query("SELECT * FROM user_detail WHERE login = :username")
    suspend fun getUserDetail(username: String): UserDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail: UserDetailEntity)
}
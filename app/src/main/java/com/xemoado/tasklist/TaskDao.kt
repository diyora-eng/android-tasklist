package com.xemoado.tasklist
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)
}
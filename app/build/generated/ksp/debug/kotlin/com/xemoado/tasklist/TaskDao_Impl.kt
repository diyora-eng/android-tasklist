package com.xemoado.tasklist

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TaskDao_Impl(
  __db: RoomDatabase,
) : TaskDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfTask: EntityInsertAdapter<Task>

  private val __deleteAdapterOfTask: EntityDeleteOrUpdateAdapter<Task>
  init {
    this.__db = __db
    this.__insertAdapterOfTask = object : EntityInsertAdapter<Task>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `tasks` (`id`,`text`) VALUES (nullif(?, 0),?)"

      protected override fun bind(statement: SQLiteStatement, entity: Task) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.text)
      }
    }
    this.__deleteAdapterOfTask = object : EntityDeleteOrUpdateAdapter<Task>() {
      protected override fun createQuery(): String = "DELETE FROM `tasks` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Task) {
        statement.bindLong(1, entity.id)
      }
    }
  }

  public override suspend fun insert(task: Task): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTask.insert(_connection, task)
  }

  public override suspend fun delete(task: Task): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfTask.handle(_connection, task)
  }

  public override fun getAll(): Flow<List<Task>> {
    val _sql: String = "SELECT * FROM tasks ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("tasks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfText: Int = getColumnIndexOrThrow(_stmt, "text")
        val _result: MutableList<Task> = mutableListOf()
        while (_stmt.step()) {
          val _item: Task
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpText: String
          _tmpText = _stmt.getText(_columnIndexOfText)
          _item = Task(_tmpId,_tmpText)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}

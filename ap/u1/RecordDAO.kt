package de.thm.ap.records.data

import android.content.Context
import de.thm.ap.records.model.Record
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RecordDAO private constructor(private val ctx: Context) {
  private var nextId = 1
  private val records = initRecords()

  fun findAll(): List<Record> = records.toList()

  fun findById(id: Int): Record? = TODO()

  /**
   * Ersetzt das übergebene [Record] Objekt mit einem bereits gespeicherten [Record] Objekt mit gleicher id.
   *
   * @param record
   * @return true = update ok, false = kein [Record] Objekt mit gleicher id im Speicher gefunden
   */
  fun update(record: Record): Boolean = TODO()

  /**
   * Persistiert das übergebene [Record] Objekt und liefert die neue id zurück.
   * (Seiteneffekte: nextId += 1 )
   *
   * @param record
   * @return neue record id
   */
  fun persist(record: Record): Int = TODO()

  /**
   * Löscht das übergebene [Record] Objekt anhand der id aus dem Speicher.
   *
   * @param record
   * @return true = ok, false = kein [Record] Objekt mit gleicher id im Speicher gefunden
   */
  fun delete(record: Record): Boolean = TODO()

  private fun initRecords(): MutableList<Record> {
    val records = mutableListOf<Record>()
    val f = ctx.getFileStreamPath(FILE_NAME)
    if (f.exists()) {
      ctx.openFileInput(FILE_NAME).use { fin ->
        ObjectInputStream(fin).readObject().let { obj ->
          records.addAll(obj as MutableList<Record>)
          // init next id
          if (records.size > 0) {
            nextId = records.maxOf { it.id ?: 0 } + 1
          }
        }

      }
    } else {
      // add some test records
      records.addAll(createTestRecords(nextId))
      nextId = records.maxOf { it.id ?: 0 } + 1
      saveRecords(records)
    }

    return records
  }

  private fun saveRecords() {
    ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
      .use { ObjectOutputStream(it).writeObject(records) }
  }

  companion object {
    private const val FILE_NAME = "records.obj"

    fun createTestRecords(startId: Int = 1): List<Record> {
      var nextId = startId
      return listOf(
        Record(
          id = nextId++,
          moduleNum = "CS1013",
          moduleName = "Objektorientierte Programmierung",
          year = 2016,
          isSummerTerm = true,
          isHalfWeighted = true,
          crp = 6,
          mark = 73
        ),
        Record(nextId++, "MN1007", "Diskrete Mathematik", 2016, false, true, 6, 81),
        Record(nextId++, "CS1019", "Compilerbau", 2017, false, false, 6, 81),
        Record(nextId, "CS1020", "Datenbanksysteme", 2017, false, false, 6, 92)
      )
    }

    @SuppressLint("StaticFieldLeak")
    @Volatile
    private var INSTANCE: RecordDAO? = null

    fun get(context: Context): RecordDAO {
      return INSTANCE ?: synchronized(this) {
        RecordDAO(context.applicationContext).also { INSTANCE = it }
      }
    }
  }
}

package de.thm.ap.records

import de.thm.ap.records.model.Record
import de.thm.ap.records.model.Stats
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

class StatsTest {
  @Test
  fun testSumCrp() {
    Assert.assertEquals(24, STATS.sumCrp)
  }

  @Test
  fun testCrpToEnd() {
    Assert.assertEquals(156, STATS.crpToEnd)
  }

  @Test
  fun testSumHalfWeighted() {
    Assert.assertEquals(2, STATS.sumHalfWeighted)
  }

  @Test
  fun testAverageMark() {
    Assert.assertEquals(83, STATS.averageMark.toLong())
  }

  companion object {
    private lateinit var STATS: Stats

    @BeforeClass
    @JvmStatic
    fun setUp() {
      STATS = Stats(
        listOf(
          Record(
            id = null,
            moduleNum = "CS1013",
            moduleName = "Objektorientierte Programmierung",
            year = 2016,
            isSummerTerm = true,
            isHalfWeighted = true,
            crp = 6,
            mark = 73
          ),
          Record(null, "MN1007", "Diskrete Mathematik", 2016, false, true, 6, 81),
          Record(null, "CS1019", "Compilerbau", 2017, false, false, 6, 81),
          Record(null, "CS1020", "Datenbanksysteme", 2017, false, false, 6, 92)
        )
      )
    }
  }
}

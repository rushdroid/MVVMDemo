package com.rushabh.wipro_rushabh

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rushabh.wipro_rushabh.roomDB.FactDao
import com.rushabh.wipro_rushabh.roomDB.Facts
import com.rushabh.wipro_rushabh.roomDB.Row
import com.rushabh.wipro_rushabh.roomDB.WiproDB
import com.rushabh.wipro_rushabh.utils.Utils
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()
    private lateinit var mockContext: Context
    private lateinit var dao: FactDao
    private lateinit var wiproDB: WiproDB

    @Before
    fun setup() {
        mockContext = InstrumentationRegistry.getInstrumentation().targetContext
        wiproDB = WiproDB.invoke(mockContext)
        dao = wiproDB.factDAO()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.rushabh.wipro_rushabh", appContext.packageName)
    }

    @Test
    fun checkInternetConnection() {
        assertFalse(!Utils.isConnectedToNetwork(mockContext))
    }


    @Test
    fun readAndWriteToDatabase() {
        val fact = Facts()
        fact.title = "About INDIA"
        val rowData = Row()
        rowData.title = "India"
        rowData.description = "Sare jahn se accha"
        rowData.imageHref = "https://india.jpg"
        fact.rows.toMutableList().add(rowData);
        val row = dao.insert(fact)
        assertTrue((row > 0))
        val factDB = dao.getAll()[0]
        // Result of
        assertEquals(factDB.title, fact.title)
    }

    @Test
    fun deleteToDatabase() {
        val fact = Facts()
        fact.title = "About INDIA"
        val rowData = Row()
        rowData.title = "India"
        rowData.description = "Sare jahn se accha"
        rowData.imageHref = "https://india.jpg"
        fact.rows.toMutableList().add(rowData);
        val row = dao.insert(fact)
        var facts = dao.getAll()
        // Result of
        assertEquals(1, fact.rows.size)
        dao.deleteAll()
        val result = dao.getAll()
        assertEquals(0, result.size)
    }

    @After
    fun tearDown() {
        wiproDB.close()
    }

}

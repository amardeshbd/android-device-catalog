package dev.hossain.android.catalog.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.hossain.android.catalog.data.model.Device
import java.io.IOException
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseInstTest {
    private lateinit var dao: DeviceDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        dao = db.deviceDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun give_data_inserted__can_read_data_from_db() {
        val device: Device = TestUtil.createDevice()

        dao.insertSync(device)
        val allDevice = dao.getAllDeviceInfo()

        assertThat(allDevice.size, equalTo(1))

        assertThat(allDevice[0].device.modelName, equalTo("ModelX"))
    }

    @Test
    @Throws(Exception::class)
    fun give_data_inserted__can_delete_data() {
        val device: Device = TestUtil.createDevice()

        dao.insertSync(device)
        val allDevice = dao.getAllDeviceInfo()

        assertThat(allDevice.size, equalTo(1))

        dao.deleteSync(allDevice[0].device)

        assertThat(dao.getAll().size, equalTo(0))
    }

    object TestUtil {
        fun createDevice(): Device {
            return Device(modelName = "ModelX")
        }
    }
}

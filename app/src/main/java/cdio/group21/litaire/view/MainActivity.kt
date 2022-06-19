package cdio.group21.litaire.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cdio.group21.litaire.databinding.ActivityMainBinding
import com.swein.easypermissionmanager.EasyPermissionManager

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val easyPermissionManager = EasyPermissionManager(this)
		easyPermissionManager.requestPermission(
			"permission",
			"permissions are necessary",
			"setting",
			arrayOf(
				android.Manifest.permission.CAMERA,
				android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
				android.Manifest.permission.READ_EXTERNAL_STORAGE
			)
		)
	}


}
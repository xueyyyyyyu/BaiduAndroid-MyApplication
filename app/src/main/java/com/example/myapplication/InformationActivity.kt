package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.AlertDialog
import android.os.Environment
import android.util.Log
import android.widget.Button
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityInformationBinding
import com.example.myapplication.user.User
import com.example.myapplication.user.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding
    private lateinit var commitButton: Button // 添加 commitButton 变量
    private var capturedImageUri: Uri? = null // 添加 capturedImageUri 变量
    private val requiredPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            selectImageFromCameraOrGallery()
        } else {
            // 相机权限被拒绝
            // 这里你可以给出一个提示或其他处理
        }
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            if (imageUri != null) {
                binding.avatar.setImageURI(imageUri)
                capturedImageUri = imageUri // 将选择或拍照得到的图片URI保存到 capturedImageUri 变量中
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 通过 ID 查找 commitButton
        commitButton = findViewById(R.id.informationcommitbtn)

        // 设置头像 ImageView 的点击事件
        binding.avatar.setOnClickListener {
            // 在此处处理选择头像的逻辑
            showImageSelectionDialog()
        }

        // 设置 commitButton 的点击监听器
        commitButton.setOnClickListener {
            val newNickname = binding.editnick.text.toString().trim()
            if (newNickname.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("nickname", newNickname)
                resultIntent.putExtra("avatarUrl", capturedImageUri?.toString() ?: "")
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }


    }

    // 添加请求拍照或选择照片的常量
    private val REQUEST_IMAGE_PICK = 102
    private val REQUEST_IMAGE_CAPTURE = 103

    // 添加头像选择的逻辑
    private fun showImageSelectionDialog() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            // 已经有权限，执行拍照或选择照片逻辑
            selectImageFromCameraOrGallery()
        } else {
            // 请求相机和存储权限
            ActivityCompat.requestPermissions(this, requiredPermissions, REQUEST_IMAGE_PICK)
        }
    }

    private fun selectImageFromCameraOrGallery() {
        val imageSelectionDialog = AlertDialog.Builder(this)
            .setTitle("选择头像")
            .setMessage("请选择获取头像的方式")
            .setPositiveButton("拍照") { _, _ ->
                openCamera()
            }
            .setNegativeButton("相册") { _, _ ->
                openGallery()
            }
            .create()

        imageSelectionDialog.show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        takePictureLauncher.launch(intent)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            // 创建用于保存照片的临时文件
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: IOException) {
                // 处理文件创建失败的情况
                null
            }

            // 继续只有当文件创建成功时，才进行拍照
            photoFile?.let {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "com.luojunkai.app.fileprovider",  // 这里需要替换为你的 FileProvider authority
                    it
                )
                capturedImageUri = photoURI // 将图片的 URI 设置给 capturedImageUri 变量
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                takePictureLauncher.launch(intent)
            }
        } else {
            Log.d("DEBUG", "No camera app found")
        }
    }


    // 创建用于保存照片的临时文件
    private fun createImageFile(): File {
        // 创建文件名称，可以根据需要定义
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_PICK) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // 用户授予了相机和存储权限
                selectImageFromCameraOrGallery()
            } else {
                // 用户拒绝了相机或存储权限，你可以在这里给出一个提示或其他处理
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_PICK || requestCode == REQUEST_IMAGE_CAPTURE) {
                // 处理从相册选择或拍照的图片
                // 使用 capturedImageUri 获取拍照后的图片 URI
                val imageUri: Uri? = capturedImageUri
                // 在这里你可以将头像设置为选择的图片
                if (imageUri != null) {
                    binding.avatar.setImageURI(imageUri)

                    // 将图片的 URI 设置给 capturedImageUri 变量（此处已在 openCamera 方法中设置，此处可忽略）
                    // capturedImageUri = imageUri

                    // 将头像地址保存到 Room 数据库
                    lifecycleScope.launch(Dispatchers.IO) {
                        val userDatabase = UserDatabase.getDatabase(applicationContext)
                        val userId = 1 // 假设用户ID为1
                        val user = userDatabase.userDao().getUser(userId)
                        user?.let {
                            val updatedUser = User(it.uid, 0, imageUri.toString(), it.nickname)
                            userDatabase.userDao().insertOrUpdateUser(updatedUser)
                        }
                    }
                }
            }
        }
    }

}
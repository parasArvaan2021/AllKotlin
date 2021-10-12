package com.main.kotlin.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

import com.main.kotlin.adapter.AdapterRecyclerView
import com.main.kotlin.BuildConfig
import com.main.kotlin.InterfaceRecyclerView
import com.main.kotlin.R


class ChooseLocalFile : AppCompatActivity(), InterfaceRecyclerView {
    private val REQUEST_PERMISSION_CAMERA = 100

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2
    private val OPEN_PDF=3

    lateinit var currentPhotoPath: String
    var ivImage: ImageView? = null
    val size = ArrayList<String>()
    var recyclerView:RecyclerView?=null
    var recyclerAdapter: AdapterRecyclerView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_local_file)
        val ChooseImage: Button = findViewById(R.id.ChooseImage)
        val ChooseDoc: Button = findViewById(R.id.ChooseDocument)
        recyclerView=findViewById(R.id.Recycler)
        recyclerAdapter = AdapterRecyclerView(this, size,this)
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ChooseImage.setOnClickListener(View.OnClickListener {
            val popUp: PopupMenu = PopupMenu(this, ChooseImage)
            popUp.menuInflater.inflate(R.menu.popup_menu, popUp.menu)
            popUp.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.Gallery -> {
                        openGallery()
                    }
                    R.id.Camera -> {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(
                                arrayOf(Manifest.permission.CAMERA),
                                REQUEST_PERMISSION_CAMERA
                            )

                        } else {
                            openCamera()
                        }
                    }
                }
                true
            })
            popUp.show()

        })
        ChooseDoc.setOnClickListener(View.OnClickListener {
//            val openFilePdf=Intent(Intent.ACTION_GET_CONTENT)
//            openFilePdf.setType("application/pdf")
//            startActivityForResult(Intent.createChooser(openFilePdf,"select file"),OPEN_PDF)

            selectPdfFromStorage()

        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayMessage(this, "camera permission granted")

            } else {
                displayMessage(this, "camera permission denied")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Log.i("Paras", "onActivityResult: resultcode$resultCode    req:$requestCode    data:$data")
        var uri:Uri?
        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                uri = Uri.parse(currentPhotoPath)
                val path= uri?.let { uriToPath(it) }

                Log.i("paras", "Camera: $uri\n PAth:$path")
                if (path != null) {
                    size.add(path)
                }
//                Glide.with(this)
//                    .load(uri)
//                    .placeholder(R.drawable.ic_heart)
//                    .error(R.drawable.eggs)
//                    .into(ivImage!!)
            }

            else if (requestCode == REQUEST_PICK_IMAGE && data?.data!=null) {

                uri = data?.data
                val path=data?.data?.path
                if (path != null) {
                    size.add(path)
                }
                Log.i("paras", "Gallery: $uri\n  path:$path ")
//                Glide.with(this)
//                    .load(uri)
//                    .placeholder(R.drawable.ic_heart)
//                    .error(R.drawable.eggs)
//                    .into(ivImage!!)
            }
            else if (requestCode==OPEN_PDF){
                val path: String? = data?.data?.path
                if (path != null) {
                    size.add(path)
                }
                if (data != null) {
                    Log.i("paras", "pdf: $path")
                }
            }
        }

        recyclerView?.adapter = recyclerAdapter
        recyclerAdapter?.notifyDataSetChanged()

    }

    private fun displayMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {

                val photoFile: File? = try {
                    createCapturedPhoto()
                } catch (ex: IOException) {
                    displayMessage(this, ex.message.toString())
                    null
                }

                photoFile?.also {
                    val photoURI = FileProvider.getUriForFile(
                        this,
                        "${BuildConfig.APPLICATION_ID}.fileprovider",
                        it
                    )
                    currentPhotoPath = photoURI.toString()
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    // Log.i("Paras", "openCamera: $photoURI")
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
       // intent.action =
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Picture"
            ), REQUEST_PICK_IMAGE
        )

    }
    private fun selectPdfFromStorage() {
        Toast.makeText(this, "selectPDF", Toast.LENGTH_LONG).show()
        val browseStorage = Intent(Intent.ACTION_OPEN_DOCUMENT)
        browseStorage.type = "application/pdf"
        browseStorage.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(browseStorage, "Select PDF"), OPEN_PDF
        )
    }


    @Throws(IOException::class)
    private fun createCapturedPhoto(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val fileData = File.createTempFile("PHOTO_${timestamp}", ".jpg", storageDir)
        currentPhotoPath = fileData.absolutePath
        // Log.i("paras", "createCapturedPhoto: $currentPhotoPath")

        return fileData

    }

    override fun delete(position: Int) {
        size.removeAt(position)
        recyclerAdapter?.notifyDataSetChanged()
    }

    override fun showImage(uri:String) {

        var fileName = uri.substring(uri.lastIndexOf(':') + 1)
        var filePath =File( Environment.getExternalStorageDirectory().absolutePath.toString() + "/$fileName")
        val uriForDownloadFile: Uri = FileProvider.getUriForFile(
            this@ChooseLocalFile,
            "com.main.kotlin" + ".fileprovider",
            filePath
        )

        val intent = Intent(Intent.ACTION_VIEW)
        val intentImage=Intent(Intent.ACTION_GET_CONTENT)
        Log.i("paras", "showImage: $fileName\n pathtoString:$filePath   PAth:$uri")

        if (fileName.endsWith("pdf")) {

            intent.setDataAndType(uriForDownloadFile, "application/pdf")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent)
        } else if (fileName.endsWith("jpg")) {
            intent.setDataAndType(uriForDownloadFile, "image/*")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent)
        } else {
            intent.setDataAndType(uriForDownloadFile, "image/*")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent)
        }
    }

    private fun uriToPath(uri: Uri): String? {
        val backupFile = File(uri.path)
        val absolutePath = backupFile.absolutePath
        return absolutePath.substring(absolutePath.indexOf(':') + 1)
    }
}



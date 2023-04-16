package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {

//    private  lateinit var  newRecyclerView: RecyclerView
//    private lateinit var  newArrayList: ArrayList<UserBT>
//    private lateinit var  imageId: Array<Int>
//    private lateinit var heading :Array<String>
//    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dahs)

//        newRecyclerView = this.findViewById(R.id.recyclerview)
//        newRecyclerView.layoutManager =LinearLayoutManager(this)
//        newRecyclerView.setHasFixedSize(true)
//
//        newArrayList = arrayListOf()
//        getUserData()
//
//        imageId= arrayOf(
//            R.drawable.log_in
//        )
//
//
//        heading = arrayOf()

    }

//    private fun getUserData() {
//        database = FirebaseDatabase.getInstance().getReference("Departments")
//        val addValueEventListener = database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (userSnapshot in snapshot.children) {
//
//                        val user = userSnapshot.getValue(UserBT::class.java)
//                        newArrayList.add(user!!)
//                    }
//                    newRecyclerView.adapter = MyAdapter(newArrayList)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//
//        })
    }







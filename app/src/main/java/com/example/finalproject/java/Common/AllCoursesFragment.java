package com.example.finalproject.java.Common;

import android.os.Bundle;

import android.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.finalproject.R;
import com.example.finalproject.java.Database.EnrollPostApi;
import com.example.finalproject.java.Database.JsonPlaceHolderApi;
import com.example.finalproject.java.Database.RetrofitInstance;
import com.example.finalproject.java.Istructor.InstructorActivity;
import com.example.finalproject.java.Student.StudentActivity;
import com.example.finalproject.java.types.Constants;
import com.example.finalproject.java.types.CoursesId;
import com.google.firebase.auth.FirebaseUser;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllCoursesFragment extends Fragment {

    Button math,phys,az,chem,hist;
    String mail = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view  = inflater.inflate(R.layout.fragment_all_courses, container, false);

         if(StudentActivity.email==null)
             mail = InstructorActivity.email;
         else
             mail = StudentActivity.email;

         math = view.findViewById(R.id.enrollMath);
         math.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 enrollCourse(CoursesId.Math);
             }
         });
        phys = view.findViewById(R.id.enrollPhys);
        phys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollCourse(CoursesId.Phys);
            }
        });
        az = view.findViewById(R.id.enrollAz);
        az.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollCourse(CoursesId.Az);
            }
        });
        chem = view.findViewById(R.id.enrollChem);
        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollCourse(CoursesId.Chem);
            }
        });
        hist = view.findViewById(R.id.enrollHist);
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrollCourse(CoursesId.Hist);
            }
        });

        return view;
    }

    private void enrollCourse(String courseId)
    {
        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitInstance.CreateInstance(Constants.baseUrl).create(JsonPlaceHolderApi.class);
        String id = String.valueOf(UUID.randomUUID());
        Log.i("EnrollId",id);
        EnrollPostApi postApi = new EnrollPostApi(id, mail,courseId);

        Call<EnrollPostApi> call = jsonPlaceHolderApi.createPost(postApi);
        call.enqueue(new Callback<EnrollPostApi>() {
            @Override
            public void onResponse(Call<EnrollPostApi> call, Response<EnrollPostApi> response) {
                if(response.isSuccessful())
                    Log.i("Enroll", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<EnrollPostApi> call, Throwable t) {
                Log.i("Enroll", t.getMessage());
            }
        });
    }
}
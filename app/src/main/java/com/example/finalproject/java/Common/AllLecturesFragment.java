package com.example.finalproject.java.Common;

import android.os.Build;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.java.Student.MyCoursesFragment;
import com.example.finalproject.java.types.CoursesId;


public class AllLecturesFragment extends Fragment {

    RecyclerView lecturesRecy;
    String[] allLectures, mathLinks, physLinks, azLinks, chemLinks, histLinks, senderLinks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_lectures, container, false);

        lecturesRecy = view.findViewById(R.id.lecturesRecy);
        allLectures = getResources().getStringArray(R.array.allLectures);
        mathLinks = getResources().getStringArray(R.array.MathLinks);
        physLinks = getResources().getStringArray(R.array.PhysLinks);
        azLinks = getResources().getStringArray(R.array.AzLinks);
        chemLinks = getResources().getStringArray(R.array.ChemLinks);
        histLinks = getResources().getStringArray(R.array.HistLinks);

        if(MyCoursesFragment.courseId == CoursesId.Math)
            senderLinks = mathLinks;
        else if (MyCoursesFragment.courseId == CoursesId.Phys)
            senderLinks = physLinks;
        else if (MyCoursesFragment.courseId == CoursesId.Az)
            senderLinks = azLinks;
        else if (MyCoursesFragment.courseId == CoursesId.Chem)
            senderLinks = chemLinks;
        else if(MyCoursesFragment.courseId == CoursesId.Hist)
            senderLinks = histLinks;
        else {
            senderLinks = new String[mathLinks.length+physLinks.length+azLinks.length+chemLinks.length+histLinks.length];
        }
        LecturesAdapter lecturesAdapter = new LecturesAdapter(getContext(),allLectures, senderLinks);
        lecturesRecy.setAdapter(lecturesAdapter);
        lecturesRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        MyCoursesFragment.courseId = null;
        return view;
    }
}
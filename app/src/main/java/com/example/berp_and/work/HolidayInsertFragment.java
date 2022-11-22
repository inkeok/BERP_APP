package com.example.berp_and.work;

import android.os.Bundle;

import androidx.core.util.Pair;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berp_and.CommonAskTask;
import com.example.berp_and.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class HolidayInsertFragment extends BottomSheetDialogFragment {

    Button holiday_date, holiday_insert_btn, holiday_cancel_btn;
    HolidayVO vo = new HolidayVO();
    TextView holiday_start, holiday_end;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_holiday_insert, container, false);

        holiday_date = v.findViewById(R.id.holiday_date);
        holiday_insert_btn = v.findViewById(R.id.holiday_insert_btn);
        holiday_start = v.findViewById(R.id.holiday_start);
        holiday_end = v.findViewById(R.id.holiday_end);
        holiday_cancel_btn = v.findViewById(R.id.holiday_cancel_btn);


        holiday_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder =
                        MaterialDatePicker.Builder.dateRangePicker().setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar);
                builder.setTitleText("날짜를 선택해주세요");
                Log.d("test", "onClick: " + Calendar.getInstance().getTimeInMillis());
                builder.setCalendarConstraints(new CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.now()).build());


                MaterialDatePicker materialDatePicker = builder.build();


                materialDatePicker.show(getChildFragmentManager(),"DATE_PICKER");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Log.d("test", "onPositiveButtonClick: " + selection);
                        Pair<Long , Long> pair = (Pair<Long, Long>) selection;

                        Log.d("test", "onPositiveButtonClick: " +     pair.first);
                        Log.d("test", "onPositiveButtonClick: " +     new Date(pair.first));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.KOREA);

                        vo.setStart_holiday(sdf.format(new Date(pair.first)));
                        vo.setEnd_holiday(sdf.format(new Date(pair.second)));
                        holiday_start.setText("휴가 시작일 : " + vo.getStart_holiday());
                        holiday_end.setText("휴가 종료일 : " +vo.getEnd_holiday());
                        if(vo.getStart_holiday() == null || vo.getEnd_holiday() ==null){
                            Toast.makeText(getContext(),"시작 날짜와 종료 날짜를 입력하세요",1000*3).show();
                            holiday_insert_btn.isEnabled();
                        }else {
                            insert_btn();

                        }
                    }
                });

            }
        });



        holiday_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Log.d("TAG", "onCreateView: ");
        return v;
    }

        public void holiday(){
            CommonAskTask askTask = new CommonAskTask("andHoliday",getActivity());

            askTask.addParam("vo",  new Gson().toJson(vo));

            askTask.executeAsk(new CommonAskTask.AsynkTaskCallback() {
                @Override
                public void onResult(String data, boolean isResult) {
                    Log.d("TAG", "onResult: "+data);
                        
                }
            });

        }


    public void insert_btn(){
        holiday_insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holiday();
                Toast.makeText(getContext(),"휴가 신청 완료",1000*3).show();

            }
        });
    }
}

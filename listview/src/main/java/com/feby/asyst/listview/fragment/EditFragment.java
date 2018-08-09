package com.feby.asyst.listview.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.feby.asyst.listview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    EditFragment.OnSubmitButtonListener listener;

    EditText etNama;
    Button btnSubmit;
    int position;


    public EditFragment() {
        // Required empty public constructor
    }

    public static EditFragment newInstance(String nama, int position) {

        EditFragment fragment = new EditFragment();

        Bundle bundle = new Bundle();
        bundle.putString("nama", nama);
        bundle.putInt("position", position);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        etNama = view.findViewById(R.id.nama_edittext);
        btnSubmit = view.findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(this);

        if (getArguments() != null) {
            etNama.setText(getArguments().getString("nama", ""));
            position = getArguments().getInt("position", 0);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                listener.onSubmitButton(etNama.getText().toString(), position);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof EditFragment.OnSubmitButtonListener) {
            listener = (EditFragment.OnSubmitButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + "activity harus implemen OnSubmitListener");
        }
    }

    public interface OnSubmitButtonListener {
        void onSubmitButton(String nama, int position);
    }

}

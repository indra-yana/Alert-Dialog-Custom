package com.indrayana.alertdialogcustom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.Constants;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int i = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] btnIds = {
                R.id.basic_test, R.id.styled_text_and_stroke, R.id.basic_test_without_buttons, R.id.under_text_test,
                R.id.error_text_test, R.id.success_text_test, R.id.warning_confirm_test, R.id.warning_cancel_test,
                R.id.custom_img_test, R.id.progress_dialog, R.id.neutral_btn_test, R.id.disabled_btn_test, R.id.dark_style,
                R.id.custom_view_test, R.id.custom_btn_colors_test, R.id.custom_success_dialog, R.id.custom_warning_dialog,
                R.id.custom_error_dialog, R.id.custom_input_dialog
        };

        for (Integer id : btnIds) {
            findViewById(id).setOnClickListener(this);
            findViewById(id).setOnTouchListener(Constants.FOCUS_TOUCH_LISTENER);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.basic_test:
                SweetAlertDialog sd = new SweetAlertDialog(this);
                sd.setCancelable(true);
                sd.setCanceledOnTouchOutside(true);
                sd.setContentText("Here's a message");
                sd.show();
                break;
            case R.id.basic_test_without_buttons:
                SweetAlertDialog sd2 = new SweetAlertDialog(this);
                sd2.setCancelable(true);
                sd2.setCanceledOnTouchOutside(true);
                sd2.setContentText("Here's a message");
                sd2.hideConfirmButton();
                sd2.show();
                break;
            case R.id.under_text_test:
                new SweetAlertDialog(this)
                        .setTitleText("Title")
                        .setContentText("It's pretty, isn't it?")
                        .show();
                break;
            case R.id.styled_text_and_stroke:
                new SweetAlertDialog(this)
                        .setTitleText("<font color='red'>Red</font> title")
                        .setContentText("Big <font color='green'>green </font><b><i> bold</i></b>")
                        .setContentTextSize(21)
                        .setStrokeWidth(2)
                        .show();
                break;
            case R.id.error_text_test:
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
                break;
            case R.id.success_text_test:
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("You clicked the button!")
                        .show();
                break;
            case R.id.warning_confirm_test:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setCancelButton("Yes, delete it!", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                // reuse previous dialog instance
                                sweetAlertDialog.setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.warning_cancel_test:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setCancelText("No, cancel pls!")
                        .setConfirmText("Yes, delete it!")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance, keep widget user state, reset them if you need
                                sDialog.setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                // or you can new a SweetAlertDialog to show
                               /* sDialog.dismiss();
                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
            case R.id.custom_img_test:
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Sweet!")
                        .setContentText("Here's a custom image.")
                        .setCustomImage(R.drawable.custom_img)
                        .show();
                break;
            case R.id.progress_dialog:
                final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Loading");
                pDialog.show();
                pDialog.setCancelable(false);
                new CountDownTimer(800 * 7, 800) {
                    public void onTick(long millisUntilFinished) {
                        // you can change the progress bar color by ProgressHelper every 800 millis
                        i++;
                        switch (i) {
                            case 0:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                                break;
                        }
                    }

                    public void onFinish() {
                        i = -1;
                        pDialog.setTitleText("Success!")
                                .setConfirmText("OK")
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }.start();
                break;

            case R.id.neutral_btn_test:
                new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Title")
                        .setContentText("Three buttons dialog")
                        .setConfirmText("Confirm")
                        .setCancelText("Cancel")
                        .setNeutralText("Neutral")
                        .show();
                break;

            case R.id.disabled_btn_test:
                final SweetAlertDialog disabledBtnDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Title")
                        .setContentText("Disabled button dialog")
                        .setConfirmText("OK")
                        .setCancelText("Cancel")
                        .setNeutralText("Neutral");

                disabledBtnDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        disabledBtnDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setEnabled(false);
                    }
                });
                disabledBtnDialog.show();
                break;

            case R.id.dark_style:
                if (((CheckBox) v).isChecked()) {
                    SweetAlertDialog.DARK_STYLE = true;
                } else {
                    SweetAlertDialog.DARK_STYLE = false;
                }
                break;

            case R.id.custom_view_test:
                final EditText editText = new EditText(this);
                final CheckBox checkBox = new CheckBox(this);
                editText.setText("Some edit text");
                checkBox.setChecked(true);
                checkBox.setText("Some checkbox");

                if (SweetAlertDialog.DARK_STYLE) {
                    editText.setTextColor(Color.WHITE);
                    checkBox.setTextColor(Color.WHITE);
                }

                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.addView(editText);
                linearLayout.addView(checkBox);

                SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Custom view")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Toast.makeText(MainActivity.this, "Text: " +editText.getText().toString(), Toast.LENGTH_SHORT).show();
                                sDialog.dismissWithAnimation();
                            }
                        });
//                        .hideConfirmButton();

                dialog.setCustomView(linearLayout);
                dialog.show();
                break;
            case R.id.custom_btn_colors_test:
                new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Custom view")
                        .setCancelButton("red", null)
                        .setCancelButtonBackgroundColor(Color.RED)
                        .setNeutralButton("cyan", null)
                        .setNeutralButtonBackgroundColor(Color.CYAN)
                        .setConfirmButton("blue", null)
                        .setConfirmButtonBackgroundColor(Color.BLUE)
                        .show();
                break;
            case R.id.custom_success_dialog:
                customSuccessDialog();
                break;
            case R.id.custom_warning_dialog:
                customWarningDialog();
                break;
            case R.id.custom_error_dialog:
                customErrorDialog();
                break;
            case R.id.custom_input_dialog:
                customInputDialog();
                break;
        }
    }

    private void customSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialogStyle);

        // Custom view via layout inflater
        View view = getLayoutInflater().inflate(R.layout.dialog_success_layout, (ConstraintLayout) findViewById(R.id.layout_dialog_container));

        builder.setView(view);

        ((TextView) view.findViewById(R.id.text_title)).setText(getResources().getString(R.string.success_title));
        ((TextView) view.findViewById(R.id.text_message)).setText(getResources().getString(R.string.dummy_description));
        ((Button) view.findViewById(R.id.button_action)).setText(getResources().getString(R.string.confirm_ok));
        ((ImageView) view.findViewById(R.id.image_icon)).setImageResource(R.drawable.ic_success);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.button_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    private void customWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialogStyle);

        // Custom view via layout inflater
        View view = getLayoutInflater().inflate(R.layout.dialog_warning_layout, (ConstraintLayout) findViewById(R.id.layout_dialog_container));

        builder.setView(view);

        ((TextView) view.findViewById(R.id.text_title)).setText(getResources().getString(R.string.warning_title));
        ((TextView) view.findViewById(R.id.text_message)).setText(getResources().getString(R.string.dummy_description));
        ((Button) view.findViewById(R.id.button_action_no)).setText(getResources().getString(R.string.confirm_no));
        ((Button) view.findViewById(R.id.button_action_yes)).setText(getResources().getString(R.string.confirm_yes));
        ((ImageView) view.findViewById(R.id.image_icon)).setImageResource(R.drawable.ic_warning);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.button_action_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                Toast.makeText(MainActivity.this, "You Choose No", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.button_action_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

                Toast.makeText(MainActivity.this, "You Choose Yes", Toast.LENGTH_SHORT).show();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    private void customErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialogStyle);

        // Custom view via layout inflater
        View view = getLayoutInflater().inflate(R.layout.dialog_error_layout, (ConstraintLayout) findViewById(R.id.layout_dialog_container));

        builder.setView(view);

        ((TextView) view.findViewById(R.id.text_title)).setText(getResources().getString(R.string.error_title));
        ((TextView) view.findViewById(R.id.text_message)).setText(getResources().getString(R.string.dummy_description));
        ((Button) view.findViewById(R.id.button_action)).setText(getResources().getString(R.string.confirm_ok));
        ((ImageView) view.findViewById(R.id.image_icon)).setImageResource(R.drawable.ic_error);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.button_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

    private void customInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialogStyle);

        // Custom view via layout inflater
        final View view = getLayoutInflater().inflate(R.layout.dialog_with_input_layout, (ConstraintLayout) findViewById(R.id.layout_dialog_container));

        builder.setView(view);

        ((TextView) view.findViewById(R.id.text_title)).setText(getResources().getString(R.string.input_title));
        //((EditText) view.findViewById(R.id.edit_text_name)).setText(getResources().getString(R.string.error_title));
        //((EditText) view.findViewById(R.id.edit_text_password)).setText(getResources().getString(R.string.dummy_description));
        ((Button) view.findViewById(R.id.button_action)).setText(getResources().getString(R.string.confirm_ok));
        ((ImageView) view.findViewById(R.id.image_icon)).setImageResource(R.drawable.ic_pen);

        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.button_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) view.findViewById(R.id.edit_text_name)).getText().toString();
                String password = ((EditText) view.findViewById(R.id.edit_text_password)).getText().toString();

                Toast.makeText(MainActivity.this, "Name: " +name +" Password: " +password, Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }
}

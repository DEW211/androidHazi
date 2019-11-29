package fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DifficultyFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = "DifficultyFragment";

    public interface DifficultyFragmentInterface {
        void onOptionsFragmentResult(String difficulty);
    }

    private String[] difficulties = {"Easy", "Medium", "Hard"};
    private DifficultyFragmentInterface difficultyFragmentInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            difficultyFragmentInterface = (DifficultyFragmentInterface) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DifficultyFragmentsInterface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please select difficulty");
        builder.setItems(difficulties, this);
        AlertDialog alert = builder.create();

        return alert;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        difficultyFragmentInterface.onOptionsFragmentResult(difficulties[which]);
    }
}

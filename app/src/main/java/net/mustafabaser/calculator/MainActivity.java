package net.mustafabaser.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import net.mustafabaser.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StringBuilder currentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentInput = new StringBuilder();

        setButtonClickListener(binding.button0);
        setButtonClickListener(binding.button1);
        setButtonClickListener(binding.button2);
        setButtonClickListener(binding.button3);
        setButtonClickListener(binding.button4);
        setButtonClickListener(binding.button5);
        setButtonClickListener(binding.button6);
        setButtonClickListener(binding.button7);
        setButtonClickListener(binding.button8);
        setButtonClickListener(binding.button9);
        setButtonClickListener(binding.buttonEquals);
        setButtonClickListener(binding.buttonPlus);
        setButtonClickListener(binding.buttonClear);

        // ConstraintLayout'a tıklandığında buttonEquals ile aynı fonksiyonu çalıştıracak,
        // böylece buton için ayrılan alanın dışındaki geniş bölümde de calculateResults() fonksiyonunu çağıracak.
        binding.equalsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void setButtonClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(button.getText().toString());
            }
        });
    }

    private void onButtonClick(String buttonText) {
        switch (buttonText) {
            case "=":
                calculateResult();
                break;
            case "+":
                currentInput.append("+");
                updateDisplay();
                break;
            case "C":
                clearInput();
                break;
            default:
                currentInput.append(buttonText);
                updateDisplay();
                break;
        }
    }

    private void calculateResult() {
        try {
            // String split işlemi ile ifadeyi ayırmak:
            String[] numbers = currentInput.toString().split("\\+");

            if (numbers.length == 2) {
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                int result = num1 + num2;
                binding.textViewResult.setText(String.valueOf(result));
            }
        } catch (Exception e) {
            binding.textViewResult.setText("Error");
        }
        currentInput.setLength(0); // Hesaplama sona erdiğinde input temizler:
    }

    private void clearInput() {
        currentInput.setLength(0);
        binding.textViewResult.setText("");
    }

    private void updateDisplay() {
        binding.textViewResult.setText(currentInput.toString());
    }
}
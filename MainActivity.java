package com.example.joserunas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvFact;
    private Button btnNext;
    private FactBackend backend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvFact = findViewById(R.id.tv_fact);
        btnNext = findViewById(R.id.btn_next);

        backend = new FactBackend();


        showNextFact();


        btnNext.setOnClickListener(view -> showNextFact());
    }


    private void showNextFact() {
        String fact = backend.getRandomFact();
        if (fact != null) {
            tvFact.setText(fact);
        }

        if (backend.isFinished()) {
            btnNext.setEnabled(false);
            btnNext.setText(getString(R.string.no_more_facts)); // Correct usage
            btnNext.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray));
            tvFact.setTextColor(Color.GRAY);
        }
    }

    private static class FactBackend {

        private final String[] facts = {
                "Trivia #1: Elephants are the only animals that can't jump.",
                "Trivia #2: A blue whale's tongue weighs as much as an elephant.",
                "Trivia #3: There are more stars in the universe than grains of sand on Earth.",
                "Trivia #4: Dolphins give names to each other.",
                "Trivia #5: Fire casts no shadow.",
                "Trivia #6: Hippo milk is pink.",
                "Trivia #7: One billion seconds is equal to 31 years.",
                "Trivia #8: Sharks are older than trees.",
                "Trivia #9: A shrimp's heart is located in its head.",
                "Trivia #10: 'E' is the most used letter in the English language."
        };

        private final ArrayList<Integer> usedIndexes = new ArrayList<>();
        private final Random random = new Random();


        String getRandomFact() {
            if (isFinished()) return null;

            int index;
            do {
                index = random.nextInt(facts.length);
            } while (usedIndexes.contains(index));

            usedIndexes.add(index);
            return facts[index];
        }


        boolean isFinished() {
            return usedIndexes.size() >= facts.length;
        }
    }
}

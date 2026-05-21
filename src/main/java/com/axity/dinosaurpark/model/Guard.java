package com.axity.dinosaurpark.model;

import java.util.List;

public class Guard extends Worker {

        public Guard(int id, String name) {
            super(id, name, 300.0);
        }

        @Override
        public String getRole() {
            return "GUARD";
        }

        public void recaptureEscapedDinosaurs(List<Dinosaur> dinosaurs) {
            for (Dinosaur dino : dinosaurs) {
                if (dino.getStatus() == DinosaurStatus.ESCAPED) {
                    dino.returnToEnclosure();
                }
            }
        }
}

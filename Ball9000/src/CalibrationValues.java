public enum CalibrationValues {

        PLATFORM_HORIZONTAL(-20), WHITE_CUP_HORIZONTAL(-330), BLACK_CUP_HORIZONTAL(-600),
        PLATFORM_VERT(200), MOVE_HEIGHT_VERT(110), MOVEMENT_SPEED(300), CUP_VERT(150);

        private int value;

        CalibrationValues(int value) {
            this.value = value;
        }

        public int getValue()
        {
            return this.value;
        }
    }


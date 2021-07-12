package com.hamryt.helparty.exception.board.gymboard;

public class UpdateGymBoardFailedException extends RuntimeException {

    public UpdateGymBoardFailedException() {
        super("Update GymBoard Failed Exception");
    }

    public UpdateGymBoardFailedException(String message) {
        super(message);
    }
}

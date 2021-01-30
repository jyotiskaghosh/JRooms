package in.JRooms.app.game;

import in.JRooms.app.models.rooms.GameRoom;
import in.JRooms.app.models.rooms.RoomGame;

public interface GameFactory {
    RoomGame createGame(GameRoom gameRoom);
}
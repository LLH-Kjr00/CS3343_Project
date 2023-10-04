import Animal from "./animal";
import Player from "./player";

type Board = {
    playerA: Player;
    playerB: Player;
    squares: Array<Array<Animal | null>>;
}

export default Board;
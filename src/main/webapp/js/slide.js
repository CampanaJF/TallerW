const tiles = document.querySelectorAll(".tile");

tiles.forEach((tile) => {
  tile.addEventListener("mouseover", (e) => {
    let reachedTarget = false;

    for (let i = 0; i < tiles.length; i++) {
      // check if current tile is hovered
      if (tiles[i] == e.currentTarget) {
        reachedTarget = true;
        continue;
      }
      // if target has previous siblings, move them to the left
      if (!reachedTarget) {
        tiles[i].classList.add("shiftLeft");
      }
      // if target has next siblings, move them to the right
      if (reachedTarget) {
        tiles[i].classList.add("shiftRight");
      }

      // make other tiles darker
      tiles[i].classList.add("darker");
    }
  });

  tile.addEventListener("mouseout", () => {
    // move all tiles back to initial position
    for (let i = 0; i < tiles.length; i++) {
      tiles[i].classList.remove("shiftLeft");
      tiles[i].classList.remove("shiftRight");
      tiles[i].classList.remove("darker");
    }
  });
});
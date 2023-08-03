### Permuted Numbers Game

#### General Rules

- Open rooms;
- Private rooms;
- Users can enter open rooms or private rooms by a password to play the game;
- The password of the private room is made by the user that created the room earlier;
- To create a room user must be authenticated;
- To enter a room user does not need to be authenticated;
- To enter a specif room, user needs to search for the room by its short id;

#### The game

- The system will raffle a random number that could be a 3, 4 or 5 digits number, depending on the game difficulty (easy, normal and hard, respectively);
- One user will try to hit the number at a time;
- The first one to play will be drawn;
- The system will show to user how many right places, wrong places and right numbers but wrong place there are, for example:
The drawn number is 123, if user input a 142, the system will show: 1 right, 1 wrong and 1 permuted. The order of the response is not on numbers order, it's the default: Right, Wrong, Permuted;
- If player one hit the number, the player two will have his last chance to hit. If player two hit that last chance, the game will continue with another number;
- If player one miss the chance and player two make it, he will win the game;


### Entities

#### User
- id: string
- name: string
- email: string 
- password: string

#### Guess
- userId: string
- guessNumber: string

#### Round
- id: string
- guesses: Guess[]

#### Game
- id: string
- difficulty: 'easy' | 'normal' | 'hard'
- drawnNumber: string
- players: User[]
- rounds: Round[]
- winner: Guess
- startedAt: Date
- endedAt: Date

#### Room
- id: string
- name: string
- password: string
- game: Game
- createdAt: Date
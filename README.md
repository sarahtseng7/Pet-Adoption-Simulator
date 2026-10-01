# Pet Adoption Simulator

A Java Swing game where you run a pet shelter. Pair each pet with the adopter who suits it best, earn points and coins, and try not to overwhelm the shelter with a bad match. Built to learn UI/UX design with Java Swing.

## How to Play

1. Click **Play** on the title screen.
2. Enter the file names of your pet and adopter `.csv` files (the included `pets.csv` and `adopters.csv` work out of the box), then click **Continue**.
3. Press **Play** on the home screen to start a round. This costs 1 heart.
4. Select one pet and one adopter from the lists, read their stats, and click **Match**.
5. See the outcome, collect your points and coins, and keep matching.

### Scoring

| Outcome | Condition | Points | Coins |
| --- | --- | --- | --- |
| Successful Match | success score ≥ 50 and return risk < 30 | 10 | 100 |
| Neutral | anything in between | 5 | 50 |
| Return Risk | return risk ≥ 50 | 2 | 20 |

Coins earned are 10x the points for the match.

### How the game ends

A round ends when either of these happens:

- **You get a Return Risk match.** The shelter is overwhelmed.
- **You run out of energy.** Each match costs 50 energy.

Your score and completion time are then eligible for the leaderboard.

### Hearts, Energy, and Coins

- **Hearts** are spent to start a round. You get 5 per day.
- **Energy** is spent on matches (50 each). It refreshes to 500 per day.
- **Coins** are earned from matches and spent in the **Shop**.
- Resources refresh once 24 hours have passed since your last save.

### Shop

| Item | Cost |
| --- | --- |
| Heart | 50 coins each (up to 10 per purchase) |
| Energy | 2 coins each (up to 500 per purchase, in steps of 50) |

## How Matches Are Calculated

Each match gets a **success score** (0–100) and a **return risk** (0–100) based on the pet and the adopter.

**Success score** adds points for:

- Preferred pet type matches (+30)
- Energy levels within 2 of each other (+25), or within 4 (+10)
- Active adopter with a high-energy pet, 7 or above (+15)
- Adopter lives in a house and the pet is a dog (+15)
- Adopter has no other pets and the pet has no special needs (+10)

…and subtracts for children with a special-needs pet (-10). Adopters with allergies only score well with birds (+30 for a bird, -40 for anything else).

**Return risk** starts at 20 and goes up for energy mismatches, special-needs pets with children, type mismatches, allergies with non-bird pets, and high-energy dogs in apartments. It goes down if the adopter already has other pets or is active with a pet of energy 6 or above.

Each match also has a shelter stress value (1–10) calculated from these scores.

## Features

- **Search** pets and adopters by name or ID
- **Sort** pets by urgency (days in shelter, +5 for special needs) using merge sort
- **Leaderboard** with the top three scores, times, and dates
- **History** of past matches, sortable by compatibility
- **End-of-Day Report** with matches played, coins earned, and high score
- **Save and load**: coins, hearts, and energy persist between sessions
- **Custom data**: load your own pets and adopters from CSV files

## Running the Project

**Requirements:** Java 21 (JDK)

### Eclipse / IntelliJ

The repo is a ready-to-open project (`.classpath` / `FinalProject.iml`). Import it, make sure `src`, `img`, `petImgFile`, and `adopterImgFile` are marked as source folders, and run `PetStart`.

### Command line

From the project root:

```bash
javac -d bin src/*.java
java -cp bin PetStart
```

Run from the project root so the game can find the CSV files. The images are already included in `bin/`.

## Custom Pet and Adopter Files

The game checks each file when you click Continue and shows an error if it isn't valid. The first row must be a header.

**Pet file** (exactly 10 columns):

```
name,age,type,imagePath,similarityScore,daysInShelter,specialNeeds,energyLevel,petID,breed
Buddy,3,Dog,dog1.png,85.5,12,,8,101,Labrador
```

- `type` must be `Dog`, `Cat`, `Bird`, or `Chipmunk`
- Leave `specialNeeds` empty if there are none
- `imagePath` refers to a file in `petImgFile/`

**Adopter file** (10 or 11 columns):

```
name,age,houseType,energyLevel,lifestyle,hasChildren,hasOtherPets,preferredType,adopterID,image,hasAllergy
Sarah,28,Apartment,6,Active,false,true,Cat,201,woman-Red-Hair.png,false
```

- `houseType` is `House` or `Apartment`
- `lifestyle` is `Active`, `Calm`, or `Lazy`
- `image` refers to a file in `adopterImgFile/`
- `hasAllergy` is optional (defaults to no allergy)

Sample files included: `pets.csv`, `pets2.csv`, `adopters.csv`, `adopters2.csv`.

## Project Structure

```
├── src/                  Java source code
├── img/                  UI images and GIFs
├── petImgFile/           Pet portraits
├── adopterImgFile/       Adopter portraits
├── bin/                  Compiled classes and resources
├── pets.csv, pets2.csv           Sample pet data
├── adopters.csv, adopters2.csv   Sample adopter data
└── *.csv (gamestate, leaderboard, match_log, shelter_history)
                          Save data written by the game
```

### Key classes

| Class | Purpose |
| --- | --- |
| `PetStart` | Entry point and title screen |
| `FileUpload` | Pet/adopter file input and validation |
| `PetHome` | Main game screen: lists, search, sort, match |
| `MatchResult` | Match simulation: success score, return risk, stress |
| `MatchEnd` | Outcome screen after each match |
| `CompatibilityEngine` | Recursive search and merge sort |
| `GameState` | Hearts, energy, coins, score, streaks, daily refresh |
| `Storage` | Reads and writes the CSV save files |
| `Shop`, `Leaderboard`, `History`, `DayReport`, `Help` | Supporting screens |
| `Pet`, `Dog`, `Cat`, `Bird`, `Adopter` | Data models |


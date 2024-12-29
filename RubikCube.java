import java.util.Scanner;

public class RubikCube {

    private CubeBlock[][][] cube;

    public RubikCube() {
        // cube with 6 sides, each side has 3 rows and 3 columns
        cube = new CubeBlock[6][3][3];

        cube[0] = new CubeBlock[][] {
                { new CubeBlock('B'), new CubeBlock('B'), new CubeBlock('B') },
                { new CubeBlock('B'), new CubeBlock('B'), new CubeBlock('B') },
                { new CubeBlock('B'), new CubeBlock('B'), new CubeBlock('B') }
        };
        cube[1] = new CubeBlock[][] {
                { new CubeBlock('R'), new CubeBlock('R'), new CubeBlock('R') },
                { new CubeBlock('R'), new CubeBlock('R'), new CubeBlock('R') },
                { new CubeBlock('R'), new CubeBlock('R'), new CubeBlock('R') }
        };
        cube[2] = new CubeBlock[][] {
                { new CubeBlock('G'), new CubeBlock('G'), new CubeBlock('G') },
                { new CubeBlock('G'), new CubeBlock('G'), new CubeBlock('G') },
                { new CubeBlock('G'), new CubeBlock('G'), new CubeBlock('G') }
        };

        cube[3] = new CubeBlock[][] {
                { new CubeBlock('Y'), new CubeBlock('Y'), new CubeBlock('Y') },
                { new CubeBlock('Y'), new CubeBlock('Y'), new CubeBlock('Y') },
                { new CubeBlock('Y'), new CubeBlock('Y'), new CubeBlock('Y') }
        };

        cube[4] = new CubeBlock[][] {
                { new CubeBlock('O'), new CubeBlock('O'), new CubeBlock('O') },
                { new CubeBlock('O'), new CubeBlock('O'), new CubeBlock('O') },
                { new CubeBlock('O'), new CubeBlock('O'), new CubeBlock('O') }
        };
        cube[5] = new CubeBlock[][] {
                { new CubeBlock('W'), new CubeBlock('W'), new CubeBlock('W') },
                { new CubeBlock('W'), new CubeBlock('W'), new CubeBlock('W') },
                { new CubeBlock('W'), new CubeBlock('W'), new CubeBlock('W') }
        };
    }

    public static void main(String[] args) {

                    System.out.println();
                    System.out.println("**// WELCOME TO THE RUBIK CUBE SOLVER //**");
                    System.out.println();
                    System.out.println("INSTUCTIONS POOL");
                    System.out.println("U+ = Top layer is rotated in clockwise direction");
                    System.out.println("U- = Top layer is rotated in anti-clockwise direction");
                    System.out.println("B+ = Bottom layer is rotated in clockwise direction");
                    System.out.println("B- = Bottom layer is rotated in anti-clockwise direction");
                    System.out.println("R+ = Right hand side layer is rotated clockwise direction");
                    System.out.println("R- = Right hand side layer is rotated anti-clockwise direction");
                    System.out.println("L+ = Right hand side layer is rotated clockwise direction");
                    System.out.println("L- = Right hand side layer is rotated anti-clockwise direction");
                    System.out.println("F+ = Front face is rotated clockwise direction");
                    System.out.println("F- = Front face is rotated anti-clockwise direction");
                    System.out.println();
                    System.out.println("Follow above instructions to solve the RubikCube");
                    System.out.println("**************");
                    System.out.println("RS = To Reshuffle the Rubic cube");
                    System.out.println("EX = To exit the Rubic cube");
                    System.out.println("**************");
                    System.out.println();

        Scanner scanner = new Scanner(System.in);
        RubikCube cube = new RubikCube();
        cube.display();

        while (true) {
            System.out.println();
            System.out.println("Enter the command to Solve : U+,U-,B+,B-.R+,R-,L+,L-,F+,F-:");
            System.out.println("Enter the command to Re shuffle : RS");
            System.out.println("Enter the command to Exit : EX");
            System.out.print("Command: ");
            String command = scanner.nextLine().toUpperCase();
            if (command.equals("EX")) {
                System.out.println("Exiting the RubikCube solver........");
                System.out.println();
                break;
            } else if (command.equals("RS")) {
                System.out.println("Reshuffled RubikCube solver........");
                System.out.println();
                cube.shuffle();
                cube.display();
            } else if (command.matches("[ULRFB][+-]?")) {
                int side = "ULRFB".indexOf(command.charAt(0));
                int turns = 1;
                if (command.length() == 2) {
                    if (command.charAt(1) == '+') {
                        turns = 3;
                    } else if (command.charAt(1) == '-') {
                        turns = 1;
                    }
                }
                cube.rotate(side, turns);
                cube.display();
                if (cube.isSolved()) {
                    System.out.println("Solved the Rubik's Cube!");
                    break;
                }
            } else {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }

    public void display() {
        String[] colors = { "\u001B[34m", "\u001B[31m", "\u001B[38;5;208m", "\u001B[33m", "\u001B[32m", "\u001B[37m" };

        for (int i = 0; i < 3; i++) {
            System.out.print("      ");
            for (int j = 0; j < 3; j++) {
                int colorIndex = "BROYGW".indexOf(cube[0][i][j].getColor());
                System.out.print(colors[colorIndex] + cube[0][i][j].getColor() + " ");
            }
            System.out.println("\u001B[0m");
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 0; k < 3; k++) {
                    int colorIndex = "BROYGW".indexOf(cube[j][i][k].getColor());
                    System.out.print(colors[colorIndex] + cube[j][i][k].getColor() + " ");
                }
            }
            System.out.println("\u001B[0m");
        }

        for (int i = 0; i < 3; i++) {
            System.out.print("      ");
            for (int j = 0; j < 3; j++) {
                int colorIndex = "BROYGW".indexOf(cube[5][i][j].getColor());
                System.out.print(colors[colorIndex] + cube[5][i][j].getColor() + " ");
            }
            System.out.println("\u001B[0m");
        }
    }

    public boolean isSolved() {
        for (int i = 0; i < 6; i++) {
            char c = cube[i][0][0].getColor();

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (cube[i][j][k].getColor() != c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void shuffle() {
        for (int i = 0; i < 20; i++) {
            int side = (int) (Math.random() * 6);
            int turns = (int) (Math.random() * 3) + 1;
            rotate(side, turns);
        }
    }

    public void rotate(int side, int turns) {
        while (turns-- > 0) {
            CubeBlock[][] temp = new CubeBlock[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[i][j] = cube[side][2 - j][i];
                }
            }
            cube[side] = temp;

            CubeBlock[] tempRow = new CubeBlock[3];

            if (side == 0) {
                tempRow = cube[1][0];
                cube[1][0] = cube[2][0];
                cube[2][0] = cube[3][0];
                cube[3][0] = cube[4][0];
                cube[4][0] = tempRow;
            } else if (side == 1) {
                tempRow = cube[0][0];
                cube[0][0] = cube[4][2];
                cube[4][2] = cube[5][0];
                cube[5][0] = cube[2][0];
                cube[2][0] = tempRow;
            } else if (side == 2) {
                tempRow = cube[0][2];
                cube[0][2] = cube[4][0];
                cube[4][0] = cube[5][2];
                cube[5][2] = cube[1][0];
                cube[1][0] = tempRow;
            } else if (side == 3) {
                tempRow = cube[0][0];
                cube[0][0] = cube[1][2];
                cube[1][2] = cube[5][2];
                cube[5][2] = cube[3][0];
                cube[3][0] = tempRow;
            } else if (side == 4) {
                tempRow = cube[0][2];
                cube[0][2] = cube[2][2];
                cube[2][2] = cube[5][0];
                cube[5][0] = cube[3][2];
                cube[3][2] = tempRow;
            } else if (side == 5) {
                tempRow = cube[1][2];
                cube[1][2] = cube[2][2];
                cube[2][2] = cube[3][2];
                cube[3][2] = cube[4][2];
                cube[4][2] = tempRow;
            }
        }
    }
}

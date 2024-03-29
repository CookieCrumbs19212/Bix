package bix.utils;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Locale;

import bix.enums.StatusCode;

import static bix.Controller.terminateSession;

import static bix.utils.Constants.IDLE_TIMEOUT_DURATION_LOWER_LIMIT;
import static bix.utils.Constants.IDLE_TIMEOUT_DURATION_UPPER_LIMIT;

/**
 * <p>
 * Class to monitor the Bix session for idle sessions (i.e. the user is inactive).
 * User activity is defined as the user providing input to Bix; and so, all user inputs
 * for Bix are controlled through this class.
 *
 * </p>
 * <p>
 * Since we cannot listen for keyboard presses and keystrokes on the console with Java,
 * we instead monitor user inputs as a means to record user activity.
 * If the user is inactive for a period of time, then the Bix session is terminated.
 * </p>
 */

public final class Reader {
    private Reader(){} // Enforce non-instantiability of this class.

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Console CONSOLE = System.console();

    // Dictates how long a session can be idle in seconds before it is terminated.
    private static int IDLE_TIMEOUT_DURATION;

    /* Timer object starts a background thread.
     * TimerTask is a task that can be scheduled and linked to the Timer object.
     *
     * The purpose of this class is to terminate the current Bix session if the
     * session has been idle for a certain period of time (determined by IDLE_TIMEOUT_DURATION).
     *
     * The task in our case is terminating the current Bix session.
     * And so, the TimerTask object calls the Controller.terminateSession().
     */
    private static final Timer IDLE_SESSION_TIMER = new Timer();
    private static final TimerTask TERMINATE_IDLE_SESSION_TASK = new TimerTask() {
        @Override
        public void run() {
            SCANNER.close();
            terminateSession(StatusCode.IDLE_SESSION_TIMEOUT);
        }
    };

    /**
     * Sets a new idle session timeout duration. The upper and lower limits for the idle session timeout duration are
     * specified in the Constants class.
     *
     * @param newTimeout new idle session timeout duration in seconds
     *
     * @return the new timeout value
     */
    public static int setIdleTimeoutDuration(int newTimeout) {
        // Ensure that the new Idle Session Timeout Duration is within the upper and lower limits.
        IDLE_TIMEOUT_DURATION = Math.min(
                IDLE_TIMEOUT_DURATION_UPPER_LIMIT,
                Math.max(IDLE_TIMEOUT_DURATION_LOWER_LIMIT, newTimeout));

        return IDLE_TIMEOUT_DURATION;
    }

    /**
     * Method to read a String input from the user.
     * @param prompt The prompt to be printed to the user
     * @return String input
     */
    public static String readString(String prompt) {
        System.out.printf("\n%s", prompt);
        startIdleSessionMonitor();
        return SCANNER.nextLine().trim();
    }

    /**
     * Method to read a char input from the user.
     * @param prompt The prompt to be printed to the user
     * @return a char input
     */
    public static char readChar(String prompt) {
        System.out.printf("\n%s", prompt);
        startIdleSessionMonitor();
        return SCANNER.nextLine().trim().charAt(0);
    }

    /**
     * Method to read an integer input from the user.
     * @param prompt The prompt to be printed to the user
     * @return an int input
     */
    public static int readInt(String prompt) {
        // Loop to ensure user enters integer input.
        do {
            System.out.printf("\n%s", prompt);
            startIdleSessionMonitor();
            try {
                return SCANNER.nextInt();
            }
            catch (InputMismatchException ime) {
                System.out.println("\nError: Please provide an integer input.\n");
            }
        } while(true);
    }

    /**
     * Method to read a password input from the user.
     * @param prompt The prompt to be printed to the user
     * @return a char array containing the password input
     */
    public static char[] readPassword(String prompt) {
        System.out.printf("\n%s", prompt);
        startIdleSessionMonitor();
        return CONSOLE.readPassword();
    }

    /**
     * Gets confirmation from the user and returns a boolean.
     * Yes -> true,
     * No -> false
     *
     * @param prompt the confirmation prompt to give the user
     * @param defaultNo when true, the default response is No; when false, default response is Yes.
     *                  Default responses are returned when the user just hits the return key without
     *                  providing any textual input
     *
     * @return boolean value
     */
    public static boolean getConfirmation(String prompt, boolean defaultNo) {
        do {
            // Print prompt.
            System.out.printf("\n%s", prompt);
            startIdleSessionMonitor();

            // Get confirmation from user
            var confirmChoice = SCANNER.nextLine().trim().toUpperCase(Locale.ROOT);

            switch (confirmChoice) {
                case "Y", "YES" -> {
                    return true;
                }
                case "N", "NO" -> {
                    return false;
                }
                default -> {
                    // If the user just hit the enter key, return the default response.
                    if (confirmChoice.isEmpty())
                        return !defaultNo;
                    else
                        System.out.println("\nInvalid response provided.");
                }
            }
        } while(true);
    }

    /**
     * <p>
     * This method schedules the Bix session termination task to be executed
     * after {@code idle_timeout} amount of time has passed.
     * </p>
     * <p>
     * Every time the user provides an input to Bix, any previously scheduled session
     * termination task is cancelled by calling {@code idle_session_timer.cancel()}.
     * The task is then scheduled (or rescheduled) by calling
     * {@code idle_session_timer.schedule(terminate_idle_session_task, idle_timeout)}.
     * </p>
     */
    private static void startIdleSessionMonitor() {
        // Cancel any existing scheduled tasks in the thread.
        // This is useful when restarting the timer after user activity is detected.
        IDLE_SESSION_TIMER.cancel();

        // Scheduling the idle session termination task again.
        // Multiply IDLE_TIMEOUT by 1000 because Timer.schedule() takes arguments in milliseconds.
        IDLE_SESSION_TIMER.schedule(TERMINATE_IDLE_SESSION_TASK, IDLE_TIMEOUT_DURATION * 1000L);
    }

} // class Reader
package com.bix.utils;

public class Constants {
    private Constants() {} // Enforce non-instantiability for this class.

    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

    // Resource path to config file.
    public static final String CONFIG_FILE_RESOURCE_PATH = "config.properties";

    // Resource path to vault file.
    public static final String VAULT_RESOURCE_PATH = "vault.db";

    // URL to Bix GitHub page.
    public static final String BIX_GITHUB_URL = "https://github.com/CookieCrumbs19212/Bix";

    // Main menu options String.
    public static final String MAIN_MENU_OPTIONS = """
            Bix Main Menu:
            
            \t[0] Display Stored Accounts

            \t[1] Retrieve Account

            \t[2] Add Account

            \t[3] Update Account
            
            \t[4] Delete Account

            \t[M] More Options
            
            \t[H] Help

            \t[X] Exit Bix
            
            """;

    // Extended menu options String.
    public static final String EXT_MENU_OPTIONS = """
            Bix Extended Menu:
            
            \t[5] Reset Master Password
            
            \t[6] Change Credential Display Duration
            
            \t[7] Change Idle Timeout Duration
            
            \t[8] Import Vault
            
            \t[9] Export Vault
            
            \t[G] Open Bix GitHub Page
            
            +---------------------+
            |   DANGER ZONE:      |
            |---------------------|
            |   [P] Purge Vault   |
            |                     |
            |   [R] Reset Bix     |
            +---------------------+
            
            \t[B] Back to Main Menu
            
            \t[X] Exit Bix
            
            """;

    public static final String HELP_STRING = """
            Bix Help
            
            - Account Actions:
            
            \t[0] Display Stored Accounts - Prints the names of all the stored accounts
            
            \t[1] Retrieve Account - Retrieve a stored account's credentials
            
            \t[2] Add Account - Add a new account

            \t[3] Update Account - Update an existing account's credentials
            
            \t[4] Delete Account - Delete a stored account
            
            
            - Bix Settings:
            
            \t[5] Reset Master Password - Reset the Bix master password
                  
            \t[6] Change Credential Display Duration - Change the duration the credentials are displayed on the screen
            
            \t[7] Change Idle Timeout Duration - Change how long Bix can stay idle before terminating the session
            
            
            - Vault Actions:
            
            \t[8] Import Vault - Import a Bix vault
            
            \t[9] Export Vault - Export the Bix vault
            
            \t[P] Purge Vault - Destroy the contents of the Bix vault. Use this option if you no longer intend to use Bix
            
            
            - Reset Bix:
            
            \t[R] Reset Bix - This action will purge the Bix vault and remove the master password.
            
            
            - GitHub Page:
            
            \t[G] Open Bix GitHub Page - Open the GitHub page for Bix in the default browser
            
            """;
}

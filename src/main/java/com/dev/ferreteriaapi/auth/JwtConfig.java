package com.dev.ferreteriaapi.auth;

public class JwtConfig {

    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
            + "MIIEogIBAAKCAQEAxpPh5ADmiSc/7tbdMphjUpwzWwt00MfSMLzMdexADvO9esf1\r\n"
            + "GSBWMvPr81b/n1avzvO6Sc0WWbVRpvStzWsvutzlJ1JYe/sWnbHCoqEYN7pDVApZ\r\n"
            + "LfvbzlULbuoGJ3pArZPsI7FyH+UqAJpkz1voVgKGadOgfQaEfHPgWH/GZ3VmQV4Y\r\n"
            + "G4fWfgEUlrtOeTzUyJ2ZKm9HUN8nGbWdSOcS81zssIeP7hsyGDxhqrmCMnNagTLw\r\n"
            + "HjpCsKPRHFsO6y4MA/KyRlxxHzTGCHTZ3jh48beiQrrE70vfJR6DfJTfXtCfVzSJ\r\n"
            + "PeQU3K6Lx2JIrOY8NC7yb61FCSLnQQvCQ3MbkwIDAQABAoIBAB4CWzDJsg0oEVFW\r\n"
            + "xzbOMLRbRqeLo6uphuo2ne90NHoN+6SCc1fIuz4X/qBXlWnO/+KWkqB0jdwguqYw\r\n"
            + "c9OHTz8+AqNenmCeIVzTcEaDcdV6IJKivbdlSGJ2F7Q7SZLNttiEcjsypbyMNTzC\r\n"
            + "GFn92a6l0wpLIQXD5mGoYU7+gE1B/Hvci9jEBOHhzFpf1uPSkCuGNQtbrD8NhtUc\r\n"
            + "Rv6L5fEY8ZFxsl5yharyFMYlYRWfX4Ensbr3fk34rNsSBCzVPxAEZ64e6pcmfkQv\r\n"
            + "DgA3ViXWom7sVfKjBiB4h7fK/TfgfSJKZTL9mlvj9pNyf/ssXmTpYKHj0lVfOAME\r\n"
            + "maV/ZIECgYEA40eC7p/VXyY50qfwxpifOF03ukPCSDkLFtlSjWV6ymjh8XY1cVVY\r\n"
            + "gDkQg+h1+ZvaSRyuCqGYnsa156YWiAjpvmi2FXFZeCjFMWsKeMiQjrgd9HaGGWKm\r\n"
            + "ShuHLp/fV6NVVswp/tvG6U4U9BRiSNzsY/6NY9xxz7Qe/Bzk9LeAsbcCgYEA36vf\r\n"
            + "GTd2JU2uE0xbP2hvmfzPUQGKD1vQmlDZcZ/vrsU8dp3oJZ+qMBN+lml/UHjPHPV/\r\n"
            + "CChwL48tUml55Z8GX6rc4QGBtqJz/WLB/+oX0/cZQiAxPFNXmryzp/s+St0KP8lL\r\n"
            + "sOmxS/vTbbyw6zGsQnbGOKzCLE5bqb1CmS0BdQUCgYAPOzOjGkMKZV1qqKaSKJhD\r\n"
            + "Brw9V10lyezcUyQdJbfc6Lp7nBhox/duYGSi8mk1mSQ/0hQ30iyQN3DpXVAVPcOs\r\n"
            + "ZwgM19PPIaxVa38AKTDG37/iKi9CdEIApWeh0ET/coq9jmEiG0hxSjJNSTBtAaE1\r\n"
            + "8lE3aAaYjNGmw6j0XXfacQKBgAj0mLopvlFACvg61mxzggRoWuSjayOySWfkVNB3\r\n"
            + "1uDohMM7D1ByVB4YqbjcAHz4WVKPgcaR1+wPj4D9OCQ5ecfaz30y0j69wmcizSGx\r\n"
            + "/cI+4oQDRfVkYN2RMKBJLvVQZFipdp5evPqN2qlKJMtF8d+3YbhjMmMTwBfLvB7l\r\n"
            + "7mkBAoGAVXqYTFX+buHPfGD2rYOtKkXvAFTUqwEwJ52oVaAD2XgWHP6tDu2HRiJ1\r\n"
            + "WU9YLsM7AlOU4BIG7oX7nEHjFUfiXLSY/4NlYQ95evjTj8q9p23mQQqMQVe9cnS2\r\n"
            + "m9udmrKo3OsuYYWVEF/KtPsh1c73+UCn7TDASHD88R3OuMurvuc=\r\n"
            + "-----END RSA PRIVATE KEY-----"
            ;

    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n"
            + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxpPh5ADmiSc/7tbdMphj\r\n"
            + "UpwzWwt00MfSMLzMdexADvO9esf1GSBWMvPr81b/n1avzvO6Sc0WWbVRpvStzWsv\r\n"
            + "utzlJ1JYe/sWnbHCoqEYN7pDVApZLfvbzlULbuoGJ3pArZPsI7FyH+UqAJpkz1vo\r\n"
            + "VgKGadOgfQaEfHPgWH/GZ3VmQV4YG4fWfgEUlrtOeTzUyJ2ZKm9HUN8nGbWdSOcS\r\n"
            + "81zssIeP7hsyGDxhqrmCMnNagTLwHjpCsKPRHFsO6y4MA/KyRlxxHzTGCHTZ3jh4\r\n"
            + "8beiQrrE70vfJR6DfJTfXtCfVzSJPeQU3K6Lx2JIrOY8NC7yb61FCSLnQQvCQ3Mb\r\n"
            + "kwIDAQAB\r\n"
            + "-----END PUBLIC KEY-----"
            ;

}
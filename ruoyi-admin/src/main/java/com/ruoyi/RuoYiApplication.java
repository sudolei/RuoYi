package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiApplication.class, args);

        System.out.println("(♥◠‿◠)ﾉﾞ  喜悦造物启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "          _         _                       _                                        \n" +
                "  _   _  (_)  ___  | |__    _   _   _   _  (_)  _ __         ___    ___    _ __ ___  \n" +
                " | | | | | | / __| | '_ \\  | | | | | | | | | | | '_ \\       / __|  / _ \\  | '_ ` _ \\ \n" +
                " | |_| | | | \\__ \\ | | | | | |_| | | |_| | | | | | | |  _  | (__  | (_) | | | | | | |\n" +
                "  \\__, | |_| |___/ |_| |_|  \\__,_|  \\__, | |_| |_| |_| (_)  \\___|  \\___/  |_| |_| |_|\n" +
                "  |___/                             |___/                                            ");


//        System.out.println("(♥◠‿◠)ﾉﾞ  喜悦造物启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
//                " .-------.       ____     __        \n" +
//                " |  _ _   \\      \\   \\   /  /    \n" +
//                " | ( ' )  |       \\  _. /  '       \n" +
//                " |(_ o _) /        _( )_ .'         \n" +
//                " | (_,_).' __  ___(_ o _)'          \n" +
//                " |  |\\ \\  |  ||   |(_,_)'         \n" +
//                " |  | \\ `'   /|   `-'  /           \n" +
//                " |  |  \\    /  \\      /           \n" +
//                " ''-'   `'-'    `-..-'              ");
    }
}
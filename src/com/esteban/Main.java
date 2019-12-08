package com.esteban;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {

        Album album = new Album("Animal", "Fakear");

        album.addSong("Sheer-Khan", 3.45);
        album.addSong("Silver", 3.33);
        album.addSong("Animal", 3.38);
        album.addSong("La lune rousse", 3.47);
        album.addSong("My Own Sun", 4.31);
        album.addSong("Jonnhae", 3.41);
        album.addSong("Red Lines", 5.28);
        album.addSong("Le chant du monde", 4.45);
        album.addSong("De La Luz", 4.09);
        album.addSong("Lessons", 3.27);

        albums.add(album);

        album = new Album("All Glows", "Fakear");

        album.addSong("Chakra", 4.08);
        album.addSong("Lost Colours", 3.40);
        album.addSong("One Chance", 3.28);
        album.addSong("Next Life", 3.53);
        album.addSong("Tokara", 3.26);
        album.addSong("Sacred Feminine", 4.32);
        album.addSong("Sea Song", 2.39);
        album.addSong("Vision", 4.16);
        album.addSong("Lou", 3.31);
        album.addSong("Tonight", 3.48);

        albums.add(album);

        LinkedList<Song> playlist = new LinkedList<Song>();
        albums.get(0).addToPlaylist("Silver", playlist);
        albums.get(0).addToPlaylist("Animal", playlist);
        albums.get(0).addToPlaylist("Imaginary", playlist); // N'existe pas
        albums.get(0).addToPlaylist(9, playlist);

        albums.get(1).addToPlaylist("Chakra", playlist);
        albums.get(1).addToPlaylist(6, playlist);
        albums.get(1).addToPlaylist("Tokara", playlist);
        albums.get(1).addToPlaylist(12, playlist); // Il n'y a pas 12 titres

        play(playlist);
    }

    private static void play(LinkedList<Song> playlist) {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> listIterator = playlist.listIterator();
        if(playlist.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString() );
                    } else {
                        System.out.println("We have reached the end of the playlist.");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist.");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list.");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward =  true;
                        } else {
                            System.out.println("We have reached the end of the list.");
                        }
                    }
                    break;
                case 4:
                    printList(playlist);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playlist.size() > 0) {
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
            }
        }
    }

    public static void printMenu() {

        System.out.println("Available actions : \npress" +
                " 0 - to quit\n" +
                "1 - to play the next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions.\n" +
                "6 - delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playlist) {

        Iterator<Song> iterator = playlist.iterator();
        System.out.println("================================");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("================================");
    }
}

package queue;
// Queue atau antrian menggunakan konsep FIFO(First In First Out)
//Enqueue adalah proses menambahkan satu data 
//Dequeue adalah proses mengurangi satu data dimulai dari data pertama

import java.util.Scanner;//digunakan untuk mengambil inputan user dari keyboard
import java.util.InputMismatchException;//digunakan untuk membuat program tidak error ketika diberi inputan selain angka

/**
 *
 * @author Ndaru Pratiwi
 */
public class Queue {
    private static int [] queue = new int[5];//variable queue dengan tipe data integer array yang menyimpan maks 5 data
    private static int jumlahqueue = 0;//variable jumlahqueue dengan tipe data integer dengan nilai awal 0
    
    private static boolean queueData(){//method untuk memeriksa penyimpanan dataQueue apakah sudah penuh atau belum
        if (jumlahqueue < queue.length){// percabangan if jika jumlahqueue < queue.length
            return true;//jika if bernilai true maka array belum penuh dan mengembalikan boolean nilai true
        } else {
            return false;//jika if bernilai false maka array sudah penuh dan mengembalikan boolean nilai false
        }
    }
    
    private static void Enqueue(){//method untuk menambahkan queue
        int checkData = 0;//variable checkData dengan tipe data integer dengan nilai awal 0 untuk memeriksa apakah data yang di inputkan benar berupa angka
        int enqueue = 0;//variable enqueue dengan tipe data integer  dengan nilai awal 0 untuk menambahkan/menampung data yang di input user
        while(checkData == 0){//perulangan untuk memeriksa apakah data yang di inputkan berupa angka atau tidak
            System.out.println("Masukkan Data (angka) : ");// cetak  Masukkan Data (angka) :
            Scanner enqueueData = new Scanner(System.in);//membuat scanner baru untuk enqueueData
            try {
                enqueue = enqueueData.nextInt();//enqueue berisi nilai dari enqueueData dengan tipe data integer
                checkData = 1;//check data bernilai 1 jika data yang di inputkan benar
            }
            catch(InputMismatchException e){//
                System.out.println("Data yang di masukkan harus berupa angka !");//cetak Data yang dimasukkan harus berupa angka ! untuk memberikan informasi user bahwa data yang di inputkan harus berupa angka
                checkData = 0;//check data bernilai 0 jika data yang dimasukkan selain angka sehingga akan di ulang/try
            }
        }
        queue[jumlahqueue] = enqueue;//queue[jumlahqueue] berisi nilai dari enqueue
        jumlahqueue++;//jumlahqueue increment
    }
    
    private static void Dequeue(){//method untuk mengurangi satu data queue dari data pertama/index 0
        jumlahqueue--;//jumlahqueue decrement
        for (int i = 0; i < jumlahqueue; i++) {//perulangan dengan i dimulai dari 0 sampai i<jumlahqueue dengan i++
            queue[i] = queue[i+1];//queue[i] replace/berisi nilai dari queue[i+1]        
        }
        System.out.println("Data pertama dalam queue sudah dikeluarkan");//cetak Data pertama dalam queue sudah dikeluarkan
    }

    private static void viewDataQueue(){//method untuk menampilkan data queue
        System.out.println("Data dalam Queue : ");//cetak Data dalam Queue
        for (int i = 0; i < jumlahqueue; i++) {//perulangan dengan i dimulai dari 0 sampai i<jumlahqueue dengan i++
            System.out.print(" ["+i+" =>"+queue[i]+"],  ");// cetak data queue yang ada
            
        }
        System.out.println("");
    }
    
    private static void hapusQueue(){// method untuk menghapus seluruh queue
        jumlahqueue = 0;//junlahqueue replace/berisi nilai 0
    }
    
    private static void keluar(){//method untuk keluar dari program queue
        String keluar = "y";//variable keluar dengan tipe data string bernilai y
        System.out.println("Keluar dari program ? (Y/T) : ");//cetak Keluar dari program ? (Y/T) :
        keluar = new Scanner(System.in).nextLine();//membuat scanner baru keluar dengan tipe data string
        if(keluar.equalsIgnoreCase("y")){//percabangan if apakah data yang di inputkan sama dengan "y"
            System.exit(0);//jika if bernilai true maka program selesai
        }else{
            menuProgram();//jika if bernilai false maka menampilkan menu program
        }
    }
    private static void menu(int pilihanMenu){//method untuk pilihan menu program queue
        switch(pilihanMenu){//percabangan switch untuk pilihan menu 
            case 1:
                boolean check = queueData();//boolean check berisi nilai dari queueata
                if(check){//percabangan if untuk memeriksa apakah data sudah penuh/belum
                    Enqueue();//Jika if bernilai true maka akan menambahkan data
                } else{
                    System.out.println("Antrian Penuh!, kosongkan data satu terlebih dahulu ");//jika if bernilai false maka akan mencetak script ini
                }
                break;//menghentikan pernyataan case 1 
            case 2:
                Dequeue();//mengurangi data dari index 0 / data pertama
                break;//menghentikan pernyataan case 2 
            case 3:
                System.out.println("Status Data : ");//cetak Status Data :
                System.out.println("Kapasitas : "+queue.length);//cetak Kapasitas : dari queue
                System.out.println("Terisi : "+jumlahqueue);//cetak Terisi :, maksudnya jumlah data yang sudah terisi 
                break;//menghentikan pernyataan case 3
            case 4: 
                viewDataQueue();//menampilkan data queue
                break;//menghentikan pernyataan case 4
            case 5:
                hapusQueue();//membersihkan atau menghapus semua data queue
                break;//menghentikan pernyataan case 5
            case 6:
                keluar();//keluar dari program queue
                break;//menghentikan pernyataan case 6
        }
        menuProgram();//memanggil menuProgram
    }
    
    private static void menuProgram(){
        int checkData = 0;//variable checkData dengan tipe data integer dengan nilai awal 0 untuk memeriksa apakah inputan berupa angka/tidak
        int pilihanMenu = 0;//variable pilihanMenu dengan tipe data integer dengan nilai awal 0 untuk memeriksa apakah inputan berupa angka/tidak
        while (checkData == 0){//perulangan untuk memeriksa apakah data yang di inputkan berupa angka atau tidak
            System.out.println("\nMenu : ");//cetak Menu
            System.out.println("1. Enqueue");//cetak 1. Enqueue
            System.out.println("2. Dequeue");//cetak 2. Dequeue
            System.out.println("3. Status Queue");//cetak 3. Status Queue
            System.out.println("4. Tampilkan data Queue");//cetak 4. Tampilkan data Queue
            System.out.println("5. Bersihkan Queue");//cetak 5. Bersihkan Queue
            System.out.println("6. Keluar dari program");//cetak 6. Keluar dari program
            System.out.println("Pilihan Menu 1 s/d 6 : ");//cetak Pilih Menu 1 s/d 6 :
            Scanner menuOption = new Scanner(System.in);//membuat scanner baru yaitu menuOption
            try{
                pilihanMenu = menuOption.nextInt();//pilihanMenu berisi nilai dari menuOption dengan tipe data integer
                checkData = 1;//checkData berisi nilai 1 jika data yang di inputkan berupa angka
            } catch(InputMismatchException e){
                System.out.println("Masukkan harus berupa angka!");//jika data yang diinputkan selain angka maka akan mencetak script ini sehingga akan diulang/try 
            }
        }
        System.out.println("");
        menu(pilihanMenu);//untuk memanggil pilihanMenu dari inputan user
    }
    public static void main(String[] args) {
       menuProgram();//memanggil menuProgram
    }
    
}

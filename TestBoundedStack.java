

public class TestBoundedStack {

    static int pass = 0, fail = 0;

    private static void check(String string, boolean b) {
        if (b) { 
            System.out.println("  [PASS] " + string); 
            pass++; 
        }
        else{ 
            System.out.println("  [FAIL] " + string); 
            fail++; 
        }
    }


public static void main(String[] arg){
System.out.println("testBoundedStack");
    /**
     * 
     * ทดสอบค่าปกติ
     * 
     */
    
    int capacity = 20;
    BoundedStack bottles = new BoundedStack(capacity);
    bottles.push(1);    
        check("bottles !=null",bottles!=null);// ในตู้น้ำจะต้องมีขวดน้ำ 
        check("capacity ต้องมากกว่า 0 และไม่เกิน MAX_BOTTLES", capacity<=BoundedStack.MAX_BOTTLES); // ค่าความจุจะต้องมากกว่า 0 และไม่เกินค่าสูงสุดของขวดน้ำ
        check("จำนวนขวดน้ำใน bottles ต้องไม่เกิน capacity", bottles.size()<= capacity);// จำนวนขวดน้ำจะต้องไม่มากกว่าค่าความจุ
        
        
        /***
         * 
         * ทดสอบการป้อนค่า capacity เป็น 0 , ค่าติดลบ และมากกว่า MAX_BOTTLES
         * 
         */
        try {
            new BoundedStack(0); // ทดสอบใส่ค่าความจุเป็น 0
            check("ค่า capacity ต้องมากกว่า 0", false); // ค่าความจุเป็น 0 ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("ค่า capacity ต้องมากกว่า 0", true);
            
        }
        try {
            new BoundedStack(-1);// ทดสอบใส่ค่าความจุเป้นค่าติดลบ
            check("ค่า capacity ต้องไม่ติดลบ", false); // ค่าความจุติดลบ ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("ค่า capacity ต้องไม่ติดลบ", true);
            
        }

        try {
            
            new BoundedStack(21); // ทดสอบใส่ค่าความจุเกินค่าสูงสุดของขวดน้ำ
            check("ค่า capacity ต้องไม่เกิน MAX_BOTTLES", false); // ค่าความจุเกินค่าสูงสุดของขวดน้ำ ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("ค่า capacity ต้องไม่เกิน MAX_BOTTLES",true );
            
        }

        /* 
         * 
         * ทดสอบ Exception เมื่อมีการใส่ค่า null
         * 
         */

       BoundedStack testnull = new BoundedStack(1);
        try {
            testnull.push(null);
            check("Push null value must throw Exception", false); // ถ้ามีการส่งค่า null ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("Push null value must throw Exception", true); 
        }
        
        /**
         * 
         * ทดสอบการเพิ่มขวดน้ำ
         * 
         */
        BoundedStack addbottles = new BoundedStack(1);
        // ทดสอบการเพิ่มขวดน้ำจนเต็มพอดี
        addbottles.push(1); // ใส่ขวดน้ำเข้าไป 
        check("ตู้กดน้ำสามารถเติมขวดน้ำได้",addbottles.size()==1);
        try {
            addbottles.push(null); //ทดสอบการใส่ขวดน้ำเป็นค่า null
            check("ขวดน้ำไม่สามารถเป็น null ได้", false); // ถ้ามีการส่งค่า null ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            
            check("ขวดน้ำไม่สามารถเป็น null ได้", true);
        }
        
        try {
            addbottles.push(2); // ทดสอบการใส่ขวดน้ำเกินค่าความจุ
            check("ตู้กดน้ำไม่สามารถเติมขวดน้ำได้", false); // การใส่ขวดน้ำเกินค่าความจุ ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("ตู้กดน้ำไม่สามารถเติมขวดน้ำได้", true);
        }
        /***
         *
         * ทดสอบ การนำขวดน้ำออกจากตู้กดน้ำทีละขวด
         *   
         */
        BoundedStack deletebottles = new BoundedStack(2);
        
        deletebottles.push(1); // ทดสอบการใส่ขวดน้ำเข้าไป
        deletebottles.pop(); // นำขวดน้ำออก
        check("นำขวดน้ำออกจากตู้กดน้ำ",deletebottles.isEmpty()); // ตรวจสอบว่าไม่มีขวดน้ำในตู้จริง

        //ทดสอบการนำขวดน้ำออกในกรณีที่ไม่มีขวดน้ำให้นำออกแล้ว

        try {
            deletebottles.pop(); // ทดสอบการนำขวดน้ำออกแม้ไม่มีขวดน้ำในตู้ 
            check("ไม่มีขวดน้ำเหลือให้นำออกแล้ว", false); // การนำขวดน้ำออกแม้ไม่มีขวดน้ำในตู้ ต้องโยน Exception
        } catch (IllegalStateException e) {
            check("ไม่มีขวดน้ำเหลือให้นำออกแล้ว", true);
            
        }


        /***
         * 
         * ทดสอบการส่องดูขวดน้ำ 
         * 
         */

        BoundedStack peekbottles = new BoundedStack(2);
        
        peekbottles.push(1); //เพิ่มขวดน้ำขวดที่ 1
        peekbottles.push(2); //เพิ่มขวดน้ำขวดที่ 2
       
        check("เปิดดูขวดน้ำต้องเป็นขวดล่าสุด",peekbottles.peek().equals(2)); //ตรวจสอบว่าขวดน้ำเป็นขวดล่าสุดที่นำเข้าหรือไม่
        
        //ตรวจสอบค่าจำนวนขวดในตู้กดน้ำจะต้องมีค่าเท่าเดิม
        check("ตรวจสอบจำนวนขวดน้ำหลังจากการเปิดดู", peekbottles.size()==2);

        /***
         * 
         * เช็คว่าตู้กดน้ำว่างหรือไม่
         *  
         */
        
        BoundedStack checkbottles = new BoundedStack(1);
        
        check("ตอนนี้ตู้กดน้ำว่างแล้ว",checkbottles.isEmpty());
        
        //ทดสอบการดูขวดน้ำ และนำขวดน้ำออก เมื่อตู้ขวดน้ำว่าง
        
        try {
            checkbottles.peek(); // ทดสอบการดูขวดน้ำเมื่อตู้ขวดน้ำว่าง
            check("ไม่สามารถดูขวดน้ำในตู้ที่ว่างได้", false); //การดูขวดน้ำเมื่อตู้ขวดน้ำว่าง ต้องโยน Exception
        } catch (IllegalStateException e) {
            check("ไม่สามารถดูขวดน้ำในตู้ที่ว่างได้", true);
        }

        /***
         * 
         * ตรวจสอบจำนวนขวดน้ำที่มีอยู่ในตู้ปัจจุบัน
         * 
         */
        BoundedStack sizebottles = new BoundedStack(2);
        
        sizebottles.push(1); //เพิ่มขวดน้ำเข้าไปในตู้
        sizebottles.push(2);
        check("ตรวจสอบจำนวนขวดน้ำในตู้",sizebottles.size()==2); // ตรวจสอบจำนวนขวดน้ำในตู้
        
        
        /*
         * 
         * ทดสอบการสร้างโคลนตู้กดน้ำ 
         * 
         */

        BoundedStack copyBoundedStack = new BoundedStack(10);
        
        copyBoundedStack.push(1); //เพิ่มขวดน้ำเข้าไปในตู้
        copyBoundedStack.push(2);
        BoundedStack newbottles = copyBoundedStack.copy(); 
        
        //ทดสอบว่าตัวโคลนแต่ละตัวมีค่าเหมือนตัวต้นฉบับทุกตัวหรือไม่
        check("ตรวจสอบว่าตัวโคลนมีขนาดเท่าตัวเริ่มต้น",newbottles.size()==2); //ตรวจสอบจำนวนของตัวโคลนว่ามีค่าเท่าตัวจริงหรือไม่
        check("ขวดน้ำในตัวโคลนมีค่าเหมือนกัน",newbottles.peek().equals(2)); // ตรวจสอบขวดน้ำว่าเป็นแบบเดียวกันหรือไม่
        
        
        // ทดสอบว่าการเพิ่มขวดน้ำในตู้ใหม่ตู้เก่าจะต้องไม่เปลี่ยน 
        newbottles.push(1); // ทำการเพิ่มขวดเข้าไปในตู้ใหม่
        check("ตรวจสอบขวดน้ำในตู้เดิมเป็นขวดเดิมหรือไม่",copyBoundedStack.peek().equals(2)); // ขวดน้ำจะต้องเป็นค่าเดิม
        check("ตรวจสอบว่าขวดน้ำในตู้เก่าเปลี่ยนแปลงหรือไม่",  copyBoundedStack.size()==2); // การเพิ่มขวดน้ำในตู้ใหม่ตู้เก่าจะต้องไม่เปลี่ยน 
        

        /*
         * 
         * สรุปผล 
         *
         */
        
        System.out.println("==================================");
        System.out.println("Total : "+(pass + fail));
        System.out.printf("PASS %d / FAIL %d\n", pass, fail);
        System.out.println("==================================");
        
    }

}




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
        check("capacity must be greater than 0 and not exceed MAX_BOTTLES", capacity<=BoundedStack.MAX_BOTTLES); // ค่าความจุจะต้องมากกว่า 0 และไม่เกินค่าสูงสุดของขวดน้ำ
        check("The number of bottles in bottles must not exceed capacity", bottles.size()<= capacity);// จำนวนขวดน้ำจะต้องไม่มากกว่าค่าความจุ
        
        
        /***
         * 
         * ทดสอบการป้อนค่า capacity เป็น 0 , ค่าติดลบ และมากกว่า MAX_BOTTLES
         * 
         */
        try {
            new BoundedStack(0); // ทดสอบใส่ค่าความจุเป็น 0
            check("The capacity value must be greater than 0", false); // ค่าความจุเป็น 0 ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("The capacity value must be greater than 0", true);
            
        }
        try {
            new BoundedStack(-1);// ทดสอบใส่ค่าความจุเป้นค่าติดลบ
            check("The capacity value must not be negative", false); // ค่าความจุติดลบ ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("The capacity value must not be negative", true);
            
        }

        try {
            
            new BoundedStack(21); // ทดสอบใส่ค่าความจุเกินค่าสูงสุดของขวดน้ำ
            check("The capacity value must not exceed MAX_BOTTLES", false); // ค่าความจุเกินค่าสูงสุดของขวดน้ำ ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("The capacity value must not exceed MAX_BOTTLES",true );
            
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
        check("The water dispenser can be refilled with a bottle",addbottles.size()==1);
        try {
            addbottles.push(null); //ทดสอบการใส่ขวดน้ำเป็นค่า null
            check("A bottle cannot be null", false); // ถ้ามีการส่งค่า null ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            
            check("A bottle cannot be null", true);
        }
        
        try {
            addbottles.push(2); // ทดสอบการใส่ขวดน้ำเกินค่าความจุ
            check("The water dispenser cannot be refilled with a bottle", false); // การใส่ขวดน้ำเกินค่าความจุ ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("The water dispenser cannot be refilled with a bottle", true);
        }
        /***
         *
         * ทดสอบ การนำขวดน้ำออกจากตู้กดน้ำทีละขวด
         *   
         */
        BoundedStack deletebottles = new BoundedStack(2);
        
        deletebottles.push(1); // ทดสอบการใส่ขวดน้ำเข้าไป
        deletebottles.pop(); // นำขวดน้ำออก
        check("Remove a bottle from the water dispenser",deletebottles.isEmpty()); // ตรวจสอบว่าไม่มีขวดน้ำในตู้จริง

        //ทดสอบการนำขวดน้ำออกในกรณีที่ไม่มีขวดน้ำให้นำออกแล้ว

        try {
            deletebottles.pop(); // ทดสอบการนำขวดน้ำออกแม้ไม่มีขวดน้ำในตู้ 
            check("There are no bottles left to remove", false); // การนำขวดน้ำออกแม้ไม่มีขวดน้ำในตู้ ต้องโยน Exception
        } catch (IllegalStateException e) {
            check("There are no bottles left to remove", true);
            
        }


        /***
         * 
         * ทดสอบการส่องดูขวดน้ำ 
         * 
         */

        BoundedStack peekbottles = new BoundedStack(2);
        
        peekbottles.push(1); //เพิ่มขวดน้ำขวดที่ 1
        peekbottles.push(2); //เพิ่มขวดน้ำขวดที่ 2
       
        check("Peeking at a bottle must return the most recently added bottle",peekbottles.peek().equals(2)); //ตรวจสอบว่าขวดน้ำเป็นขวดล่าสุดที่นำเข้าหรือไม่
        
        //ตรวจสอบค่าจำนวนขวดในตู้กดน้ำจะต้องมีค่าเท่าเดิม
        check("Check the bottle count after peeking", peekbottles.size()==2);

        /***
         * 
         * เช็คว่าตู้กดน้ำว่างหรือไม่
         *  
         */
        
        BoundedStack checkbottles = new BoundedStack(1);
        
        check("The water dispenser is now empty",checkbottles.isEmpty());
        
        //ทดสอบการดูขวดน้ำ และนำขวดน้ำออก เมื่อตู้ขวดน้ำว่าง
        
        try {
            checkbottles.peek(); // ทดสอบการดูขวดน้ำเมื่อตู้ขวดน้ำว่าง
            check("Cannot peek at a bottle in an empty dispenser", false); //การดูขวดน้ำเมื่อตู้ขวดน้ำว่าง ต้องโยน Exception
        } catch (IllegalStateException e) {
            check("Cannot peek at a bottle in an empty dispenser", true);
        }

        /***
         * 
         * ตรวจสอบจำนวนขวดน้ำที่มีอยู่ในตู้ปัจจุบัน
         * 
         */
        BoundedStack sizebottles = new BoundedStack(2);
        
        sizebottles.push(1); //เพิ่มขวดน้ำเข้าไปในตู้
        sizebottles.push(2);
        check("Check the bottle count in the dispenser",sizebottles.size()==2); // ตรวจสอบจำนวนขวดน้ำในตู้
        
        
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
        check("Check that the clone has the same size as the original",newbottles.size()==2); //ตรวจสอบจำนวนของตัวโคลนว่ามีค่าเท่าตัวจริงหรือไม่
        check("Bottles in the clone have the same values",newbottles.peek().equals(2)); // ตรวจสอบขวดน้ำว่าเป็นแบบเดียวกันหรือไม่
        
        
        // ทดสอบว่าการเพิ่มขวดน้ำในตู้ใหม่ตู้เก่าจะต้องไม่เปลี่ยน 
        newbottles.push(1); // ทำการเพิ่มขวดเข้าไปในตู้ใหม่
        check("Check if the bottles in the original dispenser have changed",copyBoundedStack.peek().equals(2)); // ขวดน้ำจะต้องเป็นค่าเดิม
        check("Check whether the bottles in the old dispenser have changed",  copyBoundedStack.size()==2); // การเพิ่มขวดน้ำในตู้ใหม่ตู้เก่าจะต้องไม่เปลี่ยน 
        

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




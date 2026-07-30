

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
     * ทดสอบค่าปกติ
     */
    int capacity = 1;
    
    BoundedStack bottles = new BoundedStack(capacity);
        
        check("bottles !=null",bottles!=null);// ในตู้น้ำจะต้องมีขวดน้ำ 
        check("capacity ต้องมากกว่า 0 และไม่เกิน MAX_BOTTLES",bottles.size()==0); //
        check("จำนวนขวดน้ำใน bottles ต้องไม่เกิน capacity", bottles.size()<= capacity);
        
      
        /* 
         * 
         * ทดสอบ Exception เมื่อมีการใส่ค่า null
         * 
         */

       BoundedStack testnull = new BoundedStack(1);
        try {
            testnull.push(null);
            check("Push null value must throw Exception", false);// ถ้ามีการส่งค่า null ต้องโยน Exception
        } catch (IllegalArgumentException e) {
            check("Push null value must throw Exception", true); // ถ้ามีการส่งค่า null ต้องโยน Exception
        }
        
        /**
         * 
         * ทดสอบการเพิ่มขวดน้ำ
         * 
         */
        BoundedStack addbottles = new BoundedStack(2);
        addbottles.push(1);
        addbottles.push(2);

        try {
            addbottles.push(3);
            check("ตู้กดน้ำไม่สามารถเติมขวดน้ำได้", false);
            
        } catch (IllegalArgumentException e) {
           check("ตู้กดน้ำไม่สามารถเติมขวดน้ำได้", true);
        }
        
        /***
         *
         * ทดสอบ การนำขวดน้ำออกจากตู้กดน้ำทีละขวด
         *   
         */
        BoundedStack deletebottles = new BoundedStack(2);
        deletebottles.push(1);
        deletebottles.pop();
        check("นำขวดน้ำออกจากตู้กดน้ำ",deletebottles.size()==0);

        //ทดสอบการนำขวดน้ำออกในกรณีที่ไม่มีขวดน้ำให้นำออกแล้ว
        try {
            deletebottles.pop();
            check("ไม่มีขวดน้ำเหลือให้นำออกแล้ว", false);
        } catch (IllegalArgumentException e) {
            check("ไม่มีขวดน้ำเหลือให้นำออกแล้ว", true);
            
        }
        
        /***
         * 
         * เช็คว่าตู้กดน้ำว่างหรือไม่
         *  
         */
        
        BoundedStack checkbottles = new BoundedStack(1);
        
        check("ตอนนี้ตู้กดน้ำว่างแล้ว",checkbottles.isEmpty());




        /***
         * 
         * ตรวจสอบจำนวนขวดน้ำที่มีอยู่ในตู้ปัจจุบัน
         * 
         */
        BoundedStack sizebottles = new BoundedStack(20);
        sizebottles.push(1);
        sizebottles.push(1);
        check("ตรวจสอบจำนวนขวดน้ำในตู้",sizebottles.size()==2);
        

        /***
         * 
         * เช็คว่าตู้กดน้ำว่างหรือไม่
         * 
         */
        BoundedStack emptybottles = new BoundedStack(1);
        
        try {
            emptybottles.pop();
            check("ตู้กดน้ำว่างแล้ว กดมาน้ำก็ไม่ออกจ้า", false);
        } catch (IllegalArgumentException e) {
            
            check("ตู้กดน้ำว่างแล้ว กดมาน้ำก็ไม่ออกจ้า", true);
        }
        
        /*
         * 
         * ทดสอบการสร้างโคลนตู้กดน้ำ 
         * 
         */

        BoundedStack copyBoundedStack = new BoundedStack(10);
        copyBoundedStack.push(1);
        copyBoundedStack.push(2);
        BoundedStack neWbottles = copyBoundedStack.copy();

        check("ตรวจสอบว่าตัวโคลนมีขนาดเท่าตัวเริ่มต้น",neWbottles.size()==2);
        
        /*
         * 
         * ทดสอบว่าการเพิ่มขวดน้ำในตู้ใหม่ตู้เก่าจะต้องไม่เปลี่ยน 
         *
         */
        neWbottles.push(1);//ทำการเพิ่มขวดเข้าไปในตู้ใหม่
        check("ตรวจสอบว่าขวดน้ำในตู้เก่าเปลี่ยนแปลงหรือไม่",  copyBoundedStack.size()!=neWbottles.size()); //การเพิ่มขวดน้ำในตู้ใหม่ตู้เก่าจะต้องไม่เปลี่ยน 

        
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




import java.util.ArrayList;
import java.util.List;

/**
 * จัดทำโดย
 * ธเนศพล สวัสดิภาพ 6821601101
 * สุฐิตา นิลงาม 6821601569
 * 
 * BoundedStack is ADT แทนตู้กดน้ำอัดลมที่มีความจุจำกัด
 * 
 * น้ำจะถูกวางซ้อนกันเป็น stack โดยจะดึงขวดน้ำที่อยู่บนสุดออกมาก่อน
 * ใช้เป็นตู้กดน้ำขนาดเล็ก เก็บได้ไม่เกิน 20 ขวด
 * 
 */
/**
 * Abstraction Function: (Bottles,capacity)  
 *   Stack ของขวดน้ำ โดยเรียงจากล่างขึ้นบนตามตำแหน่ง 0, 1, ..., bottles.size()-1 ใน bottles
 *   ซึ่ง bottles.get(bottles.size()-1) คือขวดน้ำอยู่บนสุด
 *   และ capacity คือจำนวนขวดน้ำสูงสุดที่ชั้นรับคืนนี้เก็บได้ (ความจุของตู้กดน้ำ)
 * 
 * Representation Invariant:
 *    -bottles ต้องไม่เป็นค่าว่าง
 *    -capacity ต้องมากกว่า 0 และไม่เกิน MAX_BOTTLES
 *    -จำนวนขวดน้ำที่เก็บอยู่ใน bottles ต้องไม่เกิน capacity
 *    -ทุกค่าใน bottles ต้องไม่เป็น null
 */    
public class BoundedStack {
    public static final int MAX_BOTTLES = 20;
    private final List<Integer> bottles;
    private final int capacity;
    
    private void checkRep() {
        assert bottles != null : "bottles must not be null";
        assert capacity > 0 && capacity <= MAX_BOTTLES : "capacity must be positive and not exceed MAX_BOTTLES";
        assert bottles.size() <= capacity : "number of bottles in bottles must not exceed capacity";
        for (Integer bottle : bottles) {
            assert bottle != null : "all values in bottles must not be null";
        }
    }

    /**
     * creator 1 สร้าง BoundedStack ที่มีความจุตามที่กำหนด
     * 
     * @param capacity จำนวนขวดน้ำสูงสุดที่ตู้กดน้ำเก็บได้
     * @throws IllegalArgumentException ถ้า capacity <= 0 หรือมากกว่า MAX_BOTTLES
     */
    public BoundedStack(int capacity) {
        if (capacity <= 0 || capacity > MAX_BOTTLES) {
            throw new IllegalArgumentException("capacity must be positive and not exceed MAX_BOTTLES");
        }
        this.bottles = new ArrayList<>();
        this.capacity = capacity;
        checkRep();
    }

    /**
     * Mutator 1 เพิ่มขวดน้ำใหม่ไปบนสุดของตู้กดน้ำ
     * 
     * @param bottle ขวดน้ำที่จะเพิ่ม
     * @throws IllegalArgumentException ถ้า bottle เป็น null
     * @throws IllegalArgumentException ถ้าตู้กดน้ำเต็มแล้ว
     */
    public void push(Integer bottle) {
        if (bottle == null) throw new IllegalArgumentException("bottle must not be null");
        if (bottles.size() >= capacity) throw new IllegalArgumentException("dispenser is full, cannot add more bottles");
        bottles.add(bottle);
        checkRep();
    }

    /**
     * Mutator 2 ลบขวดน้ำบนสุดของตู้กดน้ำและคืนค่าของมัน
     * 
     * @return ขวดน้ำบนสุดของตู้กดน้ำ
     * @throws IllegalStateException ถ้าตู้กดน้ำว่างแล้ว
     */
    public Integer pop() {
        if (bottles.isEmpty()) {
            throw new IllegalStateException("dispenser is empty, cannot remove a bottle");
        }
        Integer bottle = bottles.remove(bottles.size() - 1);
        checkRep();
        return bottle;
    }

    /**
     * Observer 1 คืนค่าขวดน้ำที่อยู่บนสุดโดยไม่นำออก
     * 
     * @return ขวดน้ำบนสุด
     * @throws IllegalStateException ถ้าตู้กดน้ำว่าง
     */
    public Integer peek() {
        if (bottles.isEmpty())
            throw new IllegalStateException("dispenser is empty, cannot peek at a bottle");
        return bottles.get(bottles.size() - 1);
    }

    /**
     * Observer 2 ดูจำนวนขวดน้ำที่มีอยู่ในตู้ตอนนี้
     * 
     * @return จำนวนขวดน้ำปัจจุบัน
     */
    public int size() {
        return bottles.size();
    }

    /**
     * Observer 3 เช็คว่าตู้น้ำว่างหรือไม่
     * 
     * @return true ถ้าตู้น้ำว่าง
     */
    public boolean isEmpty() {
        return bottles.isEmpty();
    }

    /**
     * Producer 1 สร้าง BoundedStack ใหม่ที่เป็นสำเนาของตู้นี้
     * 
     * @return ตู้กดน้ำใหม่ที่เป็นสำเนาของตู้นี้
     */
    public BoundedStack copy() {
        BoundedStack copy = new BoundedStack(this.capacity);
        for (int bottle : this.bottles) {
            copy.push(bottle);
        }
        return copy;
    }
}
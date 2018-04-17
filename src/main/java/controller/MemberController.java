package controller;

import model.Entry;
import model.Member;
import repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
	
    private MemberRepository mr;
    
    public MemberController(MemberRepository newMr){    	
    	this.mr =newMr;    	
    }
    
    public void addMember(Member aMemebr) {
        mr.addMember(aMemebr);    	
    }

    public List<Member> getMembers(){return mr.getAllMembers();}

    public void addEntry(Entry oneEntry) {
        mr.addEntry(oneEntry);    	
    }

    public int addEntry(String type, String value, String id) throws Exception {
        boolean isValueValid = this.validateValue(value);
        boolean isIDValid = this.validateID(id);
        boolean isTypeValid = this.validateType(type);

        if (isValueValid && isIDValid && isTypeValid) {
            Entry newEntry = new Entry(type, Integer.parseInt(value), Integer.parseInt(id));
            mr.addEntry(newEntry);

            return 1;
        }

        return 0;
    }

    public boolean validateValue(String value) throws Exception {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }

    public boolean validateID(String id) throws Exception {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }

    public boolean validateType(String type) {
        return !type.isEmpty();
    }


//    public List<Entry> getEntriesForMember(Integer id){
//        return this.mr.getAllEntries().stream().filter((e)->{
//            return e.getIdMember() == id;
//        }).collect(Collectors.toList());
//    }

    public List<Entry> getEntriesForMember(Integer id) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (Entry entry: mr.getAllEntries()) {
            if (entry.getIdMember() == id) {
                entries.add(entry);
            }
        }

        return entries;
    }

     public List<Entry> allEntries() {
        return mr.getAllEntries();
    }
} 
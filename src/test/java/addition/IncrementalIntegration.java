package addition;

import controller.MemberController;
import model.Entry;
import model.Member;
import repository.MemberRepository;

public class IncrementalIntegration {
    MemberRepository repo;
    MemberController ctrl;

    @org.junit.Before
    public void setup() {
        repo = new MemberRepository();
        ctrl = new MemberController(repo);
    }

    @org.junit.Test
    public void addMember() {
        ctrl.addMember(new Member("Darius","1"));
        assert(ctrl.getMembers().size() == 1 || ctrl.getMembers().size() == 3 || ctrl.getMembers().size() == 5);
        ctrl.addMember(new Member("Andrei","4"));
        assert(ctrl.getMembers().size() == 2 || ctrl.getMembers().size() == 4 || ctrl.getMembers().size() == 6);
    }

    @org.junit.Test
    public void addEntry() {
        ctrl.addEntry(new Entry("cost",10,4));
        assert (ctrl.allEntries().size() == 1 || ctrl.allEntries().size() == 2);
    }

    @org.junit.Test
    public void getEntries() {
        ctrl.addEntry(new Entry("type1",25,4));
        ctrl.addEntry(new Entry("type2",35,4));

        Integer size = ctrl.getEntriesForMember(4).size();
        assert (size == 4);
    }

    public void addMemberAndEntryTests() {
        addMember();
        addEntry();
    }

    public void fullFlowTest() {
        addMember();
        addEntry();
        getEntries();
    }

    @org.junit.Test
    public void runIncrementalIntegrationTests() {
        addMember();
        addMemberAndEntryTests();
        fullFlowTest();
    }

}

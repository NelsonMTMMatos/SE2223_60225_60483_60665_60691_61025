
package net.sourceforge.ganttproject.gui;

import biz.ganttproject.core.chart.render.ShapePaint;
import biz.ganttproject.core.time.CalendarFactory;
import biz.ganttproject.core.time.GanttCalendar;
import biz.ganttproject.core.time.TimeDuration;
import net.sourceforge.ganttproject.GanttTask;
import net.sourceforge.ganttproject.task.Task;
import net.sourceforge.ganttproject.task.TaskMutator;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GanttTaskPropertiesBeanTest {

    private static final int TASK_COUNT = 5;

    @Test
    void allNamesShouldBeUpdated() {
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        String insertedTaskName = "Task name";
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setName(insertedTaskName);
            mutator.commit();

            assertEquals(insertedTaskName, task.getName());
        }
    }

    @Test
    void allLinksShouldBeUpdated(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        String insertedWebLink = "Web link";
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setWebLink(insertedWebLink);
            mutator.commit();

            assertEquals(insertedWebLink, task.getWebLink());
        }
    }

    @Test
    void allTasksShouldBeMilestones(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        boolean isMilestone = true;
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setMilestone(isMilestone);
            mutator.commit();

            assertEquals(isMilestone, task.isMilestone());
        }
    }

    @Test
    void allTasksShouldBeProjectTasks(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        boolean isProjectTask = true;
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setProjectTask(isProjectTask);
            mutator.commit();

            assertEquals(isProjectTask, task.isProjectTask());
        }
    }

    @Test
    void allTasksShouldHaveSameStart(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        GanttCalendar insertedStart = CalendarFactory.createGanttCalendar(2022, 12, 1);
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setStart(insertedStart);
            mutator.commit();

            assertEquals(insertedStart, task.getStart());
        }
    }

    @Test
    void allTasksShouldHaveSameEnd(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        GanttCalendar insertedEnd = CalendarFactory.createGanttCalendar(2022, 12, 1);
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setEnd(insertedEnd);
            mutator.commit();

            assertEquals(insertedEnd, task.getEnd());
        }
    }

    @Test
    void allTasksShouldHaveSameThird(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        GanttCalendar insertedThird = CalendarFactory.createGanttCalendar(2022, 12, 1);
        for (GanttTask task : tasks) {
            TaskMutator mutator = task.createMutator();
            mutator.setThird(insertedThird, 1);
            mutator.commit();

            assertEquals(insertedThird, task.getThird());
        }
    }

    @Test
    void allTasksShouldHaveSameLength(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        int insertedDuration = 2;
        for (GanttTask task : tasks) {
            TaskMutator mutator = task.createMutator();
            mutator.setDuration(task.getManager().createLength(insertedDuration));
            mutator.commit();

            assertEquals(insertedDuration, task.getDuration().getLength());
        }
    }

    @Test
    void allNotesShouldBeUpdated(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));
        String notes = "Task notes.";
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setNotes(notes);
            mutator.commit();

            assertEquals(notes, task.getNotes());
        }
    }

    @Test
    void allPrioritiesShouldBeUpdated(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));
        int priorityValue = 2;
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            mutator.setPriority(Task.Priority.getPriority(priorityValue));
            mutator.commit();

            assertEquals(Task.Priority.getPriority(priorityValue), task.getPriority());
        }
    }

    @Test
    void shouldSetTaskColor(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        for(GanttTask task : tasks) {
            TaskMutator mutator = task.createMutator();
            mutator.setColor(Color.PINK);
            mutator.commit();

            assert(task.getColor() == Color.PINK);
        }
    }

    @Test
    void shouldSetTaskShape(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        for(GanttTask task : tasks) {
            TaskMutator mutator = task.createMutator();
            int[] array = {1, 2, 3};
            ShapePaint shape = new ShapePaint(2,4, array, Color.WHITE, task.getColor());
            mutator.setShape(shape);
            mutator.commit();

            assert(task.getShape().equals(shape));
        }
    }

}

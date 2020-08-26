import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class DDA16301127 implements GLEventListener{

    private GLU glu;
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        DDA(gl, 4,4,7,10);

    }
    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }



    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }


    public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {

        gl.glPointSize(3.0f);
        gl.glColor3d(1, 0, 0);
        //write your own code
        float m,x,y,dx,dy;
        dx=x2-x1;
        dy=y2-y1;
        //calculating the slope.
        m=dy/dx;
        x=x1;
        y=y1;
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x1, y1);
        //If the slope is not steep that means the slope is between -1 to 1.
        if(m<=1 && m>=-1) {
            while(x<x2) {
                x=x+1;
                y=y+m;
                gl.glVertex2f(x,y);
            }
        }
        //If the slope is steep.
        else {
            while(y<=y2) {
                y++;
                x=x+(1/m);
                gl.glVertex2f(x,y);
            }
        }
        gl.glEnd();
    }






    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        DDA16301127 l = new DDA16301127();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;